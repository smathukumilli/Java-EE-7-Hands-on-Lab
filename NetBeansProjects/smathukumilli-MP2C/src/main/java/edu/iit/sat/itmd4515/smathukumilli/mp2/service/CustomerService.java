/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.smathukumilli.mp2.service;

import edu.iit.sat.itmd4515.smathukumilli.mp2.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author shanmukh
 */
@Stateless
public class CustomerService {

    private static final Logger LOG = Logger.getLogger(CustomerService.class.getName());
  @Resource(lookup="jdbc/smathukumilliMP2DS")
    private DataSource ds;
  
    /**
     *
     * @param customer
     */
    public boolean editcustomer(Customer customer){
      
        String updateSql = "update customer set "
                + "firstname=?, "
                + "lastname=? "
                + "where customerID=?";
         int returnVal = 0;

        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(updateSql);
              ps.setString(1, customer.getCustomerID());
            ps.setString(2, customer.getFirstname() );
            ps.setString(3, customer.getLastname());
           LOG.info("Updating a new record " + customer.toString());
            returnVal = ps.executeUpdate();
   }    catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return returnVal > 0;
   
    }
  
    /**
     *
     * @param customer
     * @throws SQLException
     */
    public boolean addCustomer(Customer customer) {


       /* String insertSql = "insert into customer("
                + "customerID,"
                + "firstname,"
                + "lastname)"
                + " values(?,?,?)";*/
         String insertsql = "insert into customer("
                + "customerID,"
                + "firstname,"
                + "lastname)"
                + " values(?,?,?)";    
   // String insertsql = "INSERT INTO customer(customerID, firstname, lastname) VALUES (?, ?, ?)";     
 int returnVal = 0;

        try (Connection c = ds.getConnection()) {
          

            // creating new record
            if (returnVal == 0) {
                
               PreparedStatement ps = c.prepareStatement(insertsql);
               ps.setString(1, customer.getCustomerID() );
                ps.setString(2, customer.getFirstname());
                ps.setString(3, customer.getLastname() );
              
                LOG.info("Inserting a new record " + customer.toString());
                returnVal = ps.executeUpdate();
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            LOG.info("null values are not accepted"+ex.getMessage());
            
            return false;
        }

        return returnVal > 0;
    }
  
    /**
     *
     * @param customer
     * @return 
     */
   public boolean deletecustomer(Customer customer){
       int returnVal = 0;
   /* String sql = "DELETE FROM customer WHERE customerID="+customer.getCustomerID()+ "";
     try {
          executequery(sql);
      } catch (SQLException ex) {
            LOG.log(Level.INFO, "error in deleting record {0}", ex.getMessage());
      }*/
      try (Connection c = ds.getConnection()) {

            // JDBC has both Statement and PreparedStatement
            PreparedStatement ps = c.prepareStatement("DELETE FROM customer WHERE customerID= ?");
            ps.setString(1, customer.getCustomerID() );
           
           
            ps.executeUpdate();
            LOG.info("deleted");
                      
               } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
      return returnVal > 0;
      
  }
   /* public boolean deletecustomer(Customer customer){
       int returnVal = 0;
       String delsql = "DELETE FROM customer WHERE customerID="+customer.getCustomerID() +"";
       try (Connection c = ds.getConnection()) {
   
           PreparedStatement ps = c.prepareStatement(delsql);
                                              
             LOG.info("deleting a new record " + customer.toString());
              int rowsDeleted = ps.executeUpdate();
           if (rowsDeleted > 0) {
             System.out.println("A customer was deleted successfully!");
            }
            returnVal = ps.executeUpdate();
   }    catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return returnVal > 0;
   
    } */
  
    /**
     *
     * @param id
     * @return
     */
    public Customer getCustomer(String id){
      try (Connection c = ds.getConnection()) {

            // JDBC has both Statement and PreparedStatement
            PreparedStatement ps = c.prepareStatement("select * from customer where customerID= ?");
            ps.setString(1, id);
            

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return new Customer(rs.getString("customerID"),
                            rs.getString("firstname"),
                            rs.getString("lastname"));
                        
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        // if nothing was found, return null
        return null;
  }
  
    /**
     *
     * @return
     */
    public ArrayList<Customer> getAllCutomer(){
           ArrayList<Customer> list = new ArrayList<Customer>();
           String sql = "SELECT * FROM customer";
           ResultSet rs = executefetchquery(sql);
           try{
               while(rs.next()){
                   Customer customer = new Customer();
                   customer.setCustomerID(rs.getString("customerID"));
                   customer.setFirstname(rs.getString("firstname"));
              customer.setLastname(rs.getString("lastname"));
              list.add(customer);
               }
           }catch(SQLException ex){
               LOG.info("error"+ex.getMessage());
           }      
       return list;
  }
  
    /**
     *
     * @param sql
     * @throws SQLException
     */
    public void executequery(String sql) throws SQLException{
      try{
          Connection c = ds.getConnection();
          c.createStatement().execute(sql);
          c.close();
      }catch(Exception e){
           LOG.info("error"+e.getMessage());
      }
  }

    /**
     *
     * @param sql
     * @return
     */
    public ResultSet executefetchquery(String sql){
      ResultSet rs = null;
      try{
          Connection c = ds.getConnection();
          rs = c.createStatement().executeQuery(sql);
          c.close();
      }catch(Exception e){
            LOG.info("error"+e.getMessage());
      }
      return rs;
  }
}
