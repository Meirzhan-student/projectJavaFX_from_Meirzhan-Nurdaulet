package sample;


import java.sql.*;



public class DatabaseHandler<prSt> extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;

    }


    public void signUpGuest(Guests guests) {
        String insert = "INSERT INTO  " + Const.GUEST_TABLE + "(" + Const.GUEST_NAME + "," + Const.GUEST_SURNAME + "," + Const.GUEST_LOGIN + "," + Const.GUEST_PASSWORD + "," + Const.GUEST_STAR + ")" + "VALUES(?,?,?,?,?)";


        try {
            PreparedStatement pstmt = getDbConnection().prepareStatement(insert);
            pstmt.setString(1, guests.getName());
            pstmt.setString(2, guests.getSurname());
            pstmt.setString(3, guests.getLogin());
            pstmt.setString(4, guests.getPassword());
            pstmt.setString(5, guests.getStar());
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getGuest(Guests guests) {
        ResultSet reSet = null;

        String select = "SELECT * from GUESTS where LOGIN =? AND PASSWORD =?";

        try {
            PreparedStatement pstmt = getDbConnection().prepareStatement(select);
            pstmt.setString(1, guests.getLogin());
            pstmt.setString(2, guests.getPassword());

            reSet = pstmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reSet;
    }
}


