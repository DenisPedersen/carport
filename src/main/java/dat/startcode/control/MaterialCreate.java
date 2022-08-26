package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class MaterialCreate extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException, SQLException {
        return "materialcreate";
    }
}
