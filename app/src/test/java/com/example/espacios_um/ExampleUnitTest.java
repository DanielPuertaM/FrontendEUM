package com.example.espacios_um;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.espacios_um.accesodatos.Conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void nuevo() throws SQLException, ClassNotFoundException {
        Connection con = Conexion.getConexion();
        assertTrue(con != null);
    }


}