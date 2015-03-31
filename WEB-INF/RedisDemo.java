import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import redis.clients.jedis.Jedis;

public class RedisDemo extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head><title>Redis Demo</title></head>");
		out.println("<body sytle=\"text-align:center\">");
		//res.setContentType("text/html");
		Jedis redis = new Jedis("localhost", 6379);
		if (req.getParameter("clear") != "" && req.getParameter("clear") != null) {
			redis.incr("count");
		}
		else {
			redis.set("count", "0");
		}
		out.println("<h1>" + redis.get("count") + "</h1>");
		out.println("<form method=\"get\" action=\"/RedisDemo/RedisDemo\">");
		out.println("<button type=\"submit\" name=\"clickAd\" value=\"click\">I'm an Ad. Click me!</button>");
		out.println("<button type=\"submit\" name=\"clear\" value=\"clear\">clear</button>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}
