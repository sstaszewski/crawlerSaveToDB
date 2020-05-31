package connection;
import connection.ConnectionSingleton;
import model.Car;

import java.sql.Statement;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
//		ArrayList<Car> carList = null;
//		executeInsertCarDetails(carList);
//		response.sendRedirect("carAdded.jsp");
	}

	public static void executeInsertCarDetails(ArrayList<Car> carList) {
		try {
			Connection conn = ConnectionSingleton.getConnection();
			Statement st = conn.createStatement();
			for(int i=0;i>carList.size();i++) {
			st.executeUpdate(prepareInsertStatement(carList, i));
			}
			System.out.println("ok");
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("cos nie pyklo");
		}
	}

	private static String prepareInsertStatement(ArrayList<Car> carList, int i) {
		String zapytanie = "insert into dane (model, rokprodukcji, przebieg, cena) values('" + carList.get(i).getModel() + "', '" 
				+ carList.get(i).getProductionYear() + "', '" 
				+ carList.get(i).getMileAge() + "', '" 
				+ carList.get(i).getPrice() + "'); ";
		return zapytanie;
	}
}
