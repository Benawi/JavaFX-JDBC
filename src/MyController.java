import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Types;
import oracle.jdbc.*;



;

public class MyController {


    String data1, data2;
    DbConnection obj = new DbConnection();
    Connection conn = obj.connMethod();
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private GridPane gp;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView tbl;

    private String s;

    //CONNECTION DATABASE
    public void buildData() {
        DbConnection obj1;
        Connection c;
        ResultSet rs;
        data = FXCollections.observableArrayList();
        try {

            tbl.setStyle("-fx-background-color:red; -fx-font-color:yellow ");
            obj1 = new DbConnection();
            c = obj1.connMethod();
            //clear the content of table view
            data.clear();
            tbl.getColumns().clear();
            //int id = Integer.parseInt(data1);
            //SQL FOR SELECTING ALL OF Table
            String SQL = "SELECT * from java";
            //ResultSet,Statement

            rs = c.createStatement().executeQuery(SQL);

            //ResultSet,PreparedStatement
       /*     String SQL1 = "Insert into Profile values(?,?)";
           // String SQL = "SELECT * FROM emp WHERE empno=?";
            PreparedStatement p = conn.prepareStatement(SQL1);
            p.setString(1,data1);
            p.setString(2,data2);
           Boolean status= p.execute();
*/

            //rs = p.executeQuery();

            //ResultSet,CallableStatement
            CallableStatement cstmt =null;
             cstmt = c.prepareCall("{call SELECTDATA(?)}");

             //cstmt.setString(1, data1);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //rs=cstmt.executeQuery();
            //cstmt.executeUpdate();

            //rs = (ResultSet) cstmt.getObject(1);
            //System.out.println(cstmt.getTimestamp(2));
            //rs = cstmt.executeQuery();

            cstmt = c.prepareCall("{call INSERTDATA(?,?)}");
            cstmt.setString(1,data1);
            cstmt.setString(2,data2);
            cstmt.execute();

            //ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //for (int i = 1; i < rsmd.getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                //TableColumn col = new TableColumn(rsmd.getColumnLabel(i));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tbl.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }


            while (rs.next()) {

                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row[1]added " + row);

                data.add(row);

            }


            tbl.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
    }


    public void handleButtonAction(ActionEvent event) {

        data1 = txt1.getText();
        data2 = txt2.getText();

        String query = "Insert into java(NAME,COURSE) VALUES('" + data1 + "','" + data2 + "')";
        try {

            Statement statement = conn.createStatement();
            boolean status=statement.execute(query);
            txt1.setText("");
            txt2.setText("");

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            if (!status)
            a.setContentText("successfuly inserted");
            else
                a.setContentText("Failed");
            a.showAndWait();
            // create a popup

            buildData();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
