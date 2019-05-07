/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Liam
 */
public abstract class Serialisation {
    
    protected PrintWriter getWriterWithJsonHeader(HttpServletResponse response)
            throws IOException{
        PrintWriter out = response.getWriter();
        return out;
    }
    
    public abstract void serialiser(HttpServletRequest request, HttpServletResponse response)
            throws IOException;
}
