package com.meg.module.core;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.cmd.Query;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

import static com.meg.module.util.OfyService.ofy;

/**
 * Created by meg on 8/30/17.
 */

@Log
public class AbstractRepository <I, T>{

    private final Class<?> idClass;

    private final Class<? extends T> entityClass;

    public AbstractRepository(final Class<?> idClass, final Class<? extends  T> entityClass){
        this.idClass = idClass;
        this.entityClass = entityClass;
    }

    public T saveEntity(final T obj){
        ofy().save().entity(obj).now();
        return obj;
    }

    public CollectionResponse<T> listEntity(final String cursor, final int limit) {
        log.info("Listing " + entityClass.getName() + "s");

        //Load the entity type and make the iterator/list
        Query<? extends T> query = ofy().load().type(entityClass).limit(limit);

        //check cursor
        if (cursor != null) {

            //cursor exists, so start at that point instead of at the beginning
            //public static Cursor fromWebSafeString(java.lang.String encodedCursor)
            query = query.startAt(Cursor.fromWebSafeString(cursor));
            log.finest("Cursor:" + Cursor.fromWebSafeString(cursor));
        } else {
            //cursor does not exist, so start at the beginning
            log.finest("No Cursor");
        }

        //make iterator and obj list
        final QueryResultIterator<? extends T> queryIterator = query.iterator();

        /*The application can get a web-safe string representing the cursor by calling the Cursor object's toWebSafeString() method,
         *and can later use the static method Cursor.fromWebSafeString() to reconstitute the cursor from the string.
        */
        String cursorString = queryIterator.getCursor().toWebSafeString();

        final List<T> objList = new ArrayList<>();

        //iterate through results and add them to list
        while (queryIterator.hasNext()) {
            objList.add(queryIterator.next());
        }

        //return list of entities
        return CollectionResponse.<T>builder()
                .setItems(objList)
                .setNextPageToken(cursorString)
                .build();
    }

    public T getEntity(final I id) throws NotFoundException, InternalServerErrorException {
        log.info("Getting " + entityClass.getName() + " with id: " + id);

        final T obj;

        // We can check for Long and String, because these are the only supported identifier classes.
        if (idClass == Long.class) {
            final Long parsedId = (Long) id;
            obj = ofy().load().type(entityClass).id(parsedId).now();
        } else if (idClass == String.class) {
            final String parsedId = (String) id;
            obj = ofy().load().type(entityClass).id(parsedId).now();
        } else {
            log.severe("unsupported identifier type: " + id.getClass().getName());
            throw new InternalServerErrorException("unable to update");
        }

        if (obj == null) {
            throw new NotFoundException("Could not find " + getClass().getName() + " with ID: " + id);
        }

        return obj;
    }

    public T updateEntity(final I id, final T obj) throws NotFoundException, InternalServerErrorException {
        //check if entity with id actually exists
        final T old = getEntity(id);

        // Only the stored object knows when it was created.
        if (obj instanceof BaseEntity) {
            ((BaseEntity) obj).createdDate = ((BaseEntity) old).getCreatedDate();
        }

        //save entity
        ofy().save().entity(obj).now();

        //log and return
        log.info("Updated " + entityClass.getName() + ": " + obj);
        return obj;
    }

    public void deleteEntity(final I id) throws NotFoundException {
        //check if entity with id actually exists
        checkExists(id);

        //Delete in case of Long/String id
        if (idClass == Long.class) {
            final Long parsedId = (Long) id;
            ofy().delete().type(entityClass).id(parsedId).now();
        } else if (idClass == String.class) {
            final String parsedId = (String) id;
            ofy().delete().type(entityClass).id(parsedId).now();
        }

        log.info("Deleted " + getClass().getName() + " with ID: " + id);
    }

    private void checkExists(final I id) throws NotFoundException {
        try {
            //Check for Long/String id
            if (idClass == Long.class) {
                final Long parsedId = (Long) id;
                ofy().load().type(entityClass).id(parsedId).safe();
            } else if (idClass == String.class) {
                final String parsedId = (String) id;
                ofy().load().type(entityClass).id(parsedId).safe();
            }
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find " + getClass().getName() + " with ID: " + id);
        }
    }

    public T getByField(String fieldName, String value) throws NotFoundException {
        log.info("Getting " + entityClass.getName() + " with " + fieldName + ": " + value);

        T obj = ofy().load().type(entityClass).filter(fieldName, value).first().now();

        if (obj == null) {
            throw new NotFoundException("Could not find " + getClass().getName() + " with " + fieldName + ": " + value);
        } else {
            return obj;
        }
    }


}
