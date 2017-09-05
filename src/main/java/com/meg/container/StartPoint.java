package com.meg.container;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by meg on 9/5/17.
 */


@SuppressWarnings("serial")
public class StartPoint extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
    }
}
