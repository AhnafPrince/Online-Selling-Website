/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;

/**
 *
 * @author ahnaf
 */
public class ProductInsertServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            boolean nameFlag = false, typeFlag = false, idFlag = false, priceFlag = false, availableFlag = false, soldFlag = false;
            int priceInt = 0, availableInt = 0, soldInt = 0;
            HttpSession session = request.getSession();

            /*
            
             */
            int id = Integer.parseInt(request.getParameter("id"));

            if (productDAO.checkAvailableId(id, out)) {
                idFlag = true;

            } else {
                out.println("<font color=red><b>" + id + "</b> is already in use</font>");
                idFlag = false;
            }

            String productName = request.getParameter("productName");
            if (productName == "" || productName == null) {
                out.println("<font color=red> FirstName is required!</font>");
                out.println();
                nameFlag = false;
            } else {
                nameFlag = true;
            }
            String info = request.getParameter("info");
            String type = request.getParameter("type");
            if (type == "" || type == null) {
                out.println("<font color=red> Product Type is required!</font>");
                out.println();
                typeFlag = false;
            } else {
                typeFlag = true;
            }
            String price = request.getParameter("price");
            if (price == "" || price == null) {
                out.println("<font color=red> Price is required!</font>");
                out.println();
                priceFlag = false;
            } else {
                priceFlag = true;
                priceInt = Integer.parseInt(price);
            }
            String image = request.getParameter("image");

            String available = request.getParameter("available");

            if (available == "" || available == null) {
                out.println("<font color=red> Available Quantity is required!</font>");
                out.println();
                availableFlag = false;
            } else {
                availableFlag = true;
                availableInt = Integer.parseInt(available);

            }

            String sold = request.getParameter("sold");

            if (sold == "" || sold == null) {
                out.println("<font color=red> Available Quantity is required!</font>");
                out.println();
                soldFlag = false;
            } else {
                soldFlag = true;
                soldInt = Integer.parseInt(sold);
            }

            if (idFlag == true && nameFlag == true && typeFlag == true && priceFlag == true && availableFlag == true && soldFlag == true) {

                Product newProduct = new Product(id, productName, type, priceInt, info, image, availableInt, soldInt);              
                productDAO.insertProduct(newProduct, out);

                RequestDispatcher rd = request.getRequestDispatcher("ProductServlet");
                rd.forward(request, response);

            } else {
                out.println("<font color=red> Insert failed!</font>");
                request.setAttribute("failedInsert", "Insert failed! \n please insert correct and new Data ");
                RequestDispatcher rd = request.getRequestDispatcher("product-insert-form.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductInsertServlet.class.getName()).log(Level.SEVERE, null, ex);
            //out.println("Sql exception occured ! ");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
