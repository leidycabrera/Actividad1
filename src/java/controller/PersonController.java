package controller;

import dao.PersonDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/person")
public class PersonController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PersonDAO personDAO;

    @Override
    public void init() {
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("controller.PersonController.doGet()");
        String action = request.getParameter("action");
        String cargo = request.getParameter("cargo");

        try {
            switch (action) {
                case "new" ->
                    showNewForm(request, response);
                case "insert" ->
                    insertPerson(request, response);
                case "delete" ->
                    deletePerson(request, response);
                case "edit" ->
                    showEditForm(request, response);
                case "update" ->
                    updatePerson(request, response);
                case "list" ->
                    listPerson(request, response);
                case "rols" ->
                    listRols(request, response);
                case "filtrar" ->
                    listPersonByCargo(request, response, cargo);
                default ->
                    listRols(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("controller.PersonController.doPost()");
        doGet(request, response);
    }

    private void listPerson(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        System.out.println("controller.PersonController.listPerson()");
        List<Person> listPerson = personDAO.listAllPersons();
        List<String> cargos = personDAO.getAllRols();

        request.setAttribute("listPerson", listPerson);
        request.setAttribute("cargos", cargos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("person-list.jsp");
        dispatcher.forward(request, response);
    }

    private void listRols(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        System.out.println("controller.PersonController.listRols()");
        List<String> cargos = personDAO.getAllRols();

        request.setAttribute("cargos", cargos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("person-rol.jsp");
        dispatcher.forward(request, response);
    }

    private void listPersonByCargo(HttpServletRequest request, HttpServletResponse response, String cargo) throws SQLException, ServletException, IOException {
        System.out.println("controller.PersonController.listPersonByCargo()");
        List<Person> clientes = personDAO.getClientByRol(cargo);
        request.setAttribute("listPerson", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("person-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("controller.PersonController.showNewForm()");
        request.getRequestDispatcher("person-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        System.out.println("controller.PersonController.showEditForm()");
        int id = Integer.parseInt(request.getParameter("id"));
        Person existingPerson = personDAO.getPersonById(id);
        request.setAttribute("person", existingPerson);
        request.getRequestDispatcher("person-form.jsp").forward(request, response);
    }

    private void insertPerson(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        System.out.println("controller.PersonController.insertPerson()");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String ageStr = request.getParameter("age");
        String rol = request.getParameter("rol");

        if (name == null || email == null || ageStr == null || rol == null || name.isEmpty() || email.isEmpty() || ageStr.isEmpty() || rol.isEmpty()) {
            request.setAttribute("error", "Por favor ingrese todos los datos.");
            request.getRequestDispatcher("person-form.jsp").forward(request, response);
            return;
        }

        int age = Integer.parseInt(ageStr);
        Person newPerson = new Person(age, name, email, age, rol);
        personDAO.insertPerson(newPerson);
        response.sendRedirect("index.jsp");
    }

    private void updatePerson(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("controller.PersonController.updatePerson()");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String rol = request.getParameter("rol");

        Person person = new Person(id, name, email, age, rol);
        personDAO.updatePerson(person);
        response.sendRedirect("index.jsp");
    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("controller.PersonController.deletePerson()");
        int id = Integer.parseInt(request.getParameter("id"));
        personDAO.deletePerson(id);
        response.sendRedirect("index.jsp");
    }
}
