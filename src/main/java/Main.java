import java.sql.*;

public class Main
{
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/skillbox?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "23019088";
        String sql_request = "SELECT name, COUNT(MONTH(Subscriptions.subscription_date))/" +
                "COUNT(DISTINCT MONTH(Subscriptions.subscription_date)) as avgMonth " +
                "FROM Courses JOIN Subscriptions ON Subscriptions.course_id = Courses.id " +
                "GROUP BY Courses.name";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

        Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql_request);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String avgMonth = resultSet.getString("avgMonth");
                System.out.println(name + "  -  " + avgMonth);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}