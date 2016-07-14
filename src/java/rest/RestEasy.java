/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author somebody
 */
@WebServlet(name = "RestEasy", urlPatterns = {"/RestEasy", "/RestEasy/*", "/resteasy/*", "/resteasy"})
public class RestEasy extends HttpServlet
{
    private static final long serialVersionUID = 2L;
    
    /**
     * private inner classes
     */
    private class RestRequest
    {
        // here I will actually document what pattern these match!!
        // examples /some/someTHING9.
        String regex1 = "/([a-z]+)(/)([[a-zA-Z0-9.\\-\\_]+[,]*]+)";
        // examples /some /thing /else
        String regex2 = "/([a-z]+)";
        // example just "/"
        String regex3 = "/";
        private Pattern regExPattern1 = Pattern.compile(regex1);
        private Pattern regExPattern2 = Pattern.compile(regex2);
        private Pattern regExPattern3 = Pattern.compile(regex3);        
        private final String pathInfo;
        private String reqInfo1;
        private String reqInfo2;
        
        public RestRequest(String pi) throws ServletException
        {
            // set the pathInfo
            if (pi == null)
            {
                this.pathInfo ="/";
                this.reqInfo1 = "/";
                this.reqInfo2 ="";
                return;
            }
            this.pathInfo = pi;
            // regex parse pathInfo
            Matcher matcher;
            // check what set of data they want
            matcher = regExPattern1.matcher(this.pathInfo);
            if (matcher.find())
            {
                this.reqInfo1 = matcher.group(1);
                this.reqInfo2 = matcher.group(3);
                return;
            }
            matcher = regExPattern2.matcher(this.pathInfo);
            if (matcher.find())
            {
                this.reqInfo1 = matcher.group(1);
                this.reqInfo2 = null;
                return;
            }
            matcher = regExPattern3.matcher(this.pathInfo);
            if (matcher.find())
            {
                this.reqInfo1 = "/";
                this.reqInfo2 = null;
                return;
            }
            
            throw new ServletException();
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        RestRequest reqValues = new RestRequest(request.getPathInfo());
        try (PrintWriter out = response.getWriter())
        {
            String reqInfo1 = reqValues.reqInfo1;
            String reqInfo2 = reqValues.reqInfo2;                         
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RestEasy</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RestEasy at " + request.getContextPath() + "</h1>");
            switch(reqInfo1)
            {
                case "rocky":
                    out.println("<p>Rocky Jordan</p>");
                    break;
                case "sam":
                    out.println("<p>Sam Spade</p>");
                    break;
                case "phil":
                    out.println("<p>Phillip Marlow</p>");
                    break;
                default:
                    out.println("<p>Weird Request</p>");
            }
            out.println("<p>"+this.getServletInfo()+"</p>");
            out.println("<p>reqInfo1: "+reqInfo1+"</p>");
            out.println("<p>reqInfo2: "+reqInfo2+"</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description of RestEasy servlet";
    }// </editor-fold>

}
