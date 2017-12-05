package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import service.ClienteService;

@WebServlet("/ManterClientes.do")
public class ManterClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		String acao = request.getParameter("acao");

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setFone(fone);
		cliente.setEmail(email);

		RequestDispatcher dispatcher = null;
		ClienteService service = new ClienteService();
		switch (acao) {
		case "Incluir":
			int id = service.criar(cliente);
			cliente = service.carregar(id);
			
			request.setAttribute("cliente", cliente);
			dispatcher = request.getRequestDispatcher("Cliente.jsp");
			
			break;
		case "Listar Todos":
			ArrayList<Cliente> clientes = service.listarTodos();
			
			request.setAttribute("clientes", clientes);
			
			dispatcher = request.getRequestDispatcher("ListaDeClientes.jsp");
			break;
		}
		dispatcher.forward(request, response);
	}
}
