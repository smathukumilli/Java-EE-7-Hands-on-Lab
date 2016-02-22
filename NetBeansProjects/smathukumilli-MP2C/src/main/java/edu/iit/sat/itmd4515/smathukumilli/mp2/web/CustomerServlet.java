/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.smathukumilli.mp2.web;

import edu.iit.sat.itmd4515.smathukumilli.mp2.model.Customer;
import edu.iit.sat.itmd4515.smathukumilli.mp2.service.CustomerService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author shanmukh
 */
@WebServlet(name = "CustomerServlet",
        urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CustomerServlet.class.getName());
 @Resource
    Validator validator;
    @EJB private CustomerService svc;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
      LOG.info("I am do post method");
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages);

        // needs additional checks to ensure it is actually Long and right format
       String customerID = request.getParameter("customerID");    
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String operation = request.getParameter("operation");

       Customer customer= new Customer(customerID, firstname, lastname);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    
             LOG.log(Level.INFO, "Received the following customer from user form:\t{0}", customer.toString());
        if (operation.equalsIgnoreCase("Add")) {
            svc.addCustomer(customer);
            LOG.info("insertion of record");          
            request.getRequestDispatcher("customerinfo.jsp").forward(request, response);
           
        } else if (operation.equalsIgnoreCase("Edit")) {
            svc.editcustomer(customer);
            LOG.info("updating customer list...");
           Customer searchedCustomer = svc.getCustomer(customerID);
            request.setAttribute("customer", searchedCustomer);           
        } else if (operation.equalsIgnoreCase("Delete")) {
            svc.deletecustomer(customer);
           
            request.getRequestDispatcher("customerinfo.jsp").forward(request, response);
        } else if (operation.equalsIgnoreCase("Search")) {
           Customer searchedCustomer = svc.getCustomer(customerID);
           messages.put("sucess", "Successfully found");
            request.setAttribute("customer", searchedCustomer);
            request.getRequestDispatcher("customerinfo.jsp").forward(request, response);
        }else{
            // if violations is not empty, object failed validation.
            LOG.log(Level.INFO, "There are {0} violations.", violations.size());

            for (ConstraintViolation<Customer> violation : violations) {
                LOG.log(Level.INFO, "####### {0}.{1} failed violation:\t{2} failed with message:\t{3}", new Object[]{violation.getRootBeanClass().getSimpleName(), violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage()});
            }

            // if failure, send back to user form with validation messages
            request.setAttribute("violations", violations);
            request.setAttribute("customer", customerID);
            request.getRequestDispatcher("/customerinfo.jsp").forward(request, response);
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
            throws ServletException, IOException {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
       
    
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
