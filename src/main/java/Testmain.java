import com.ss.service.UiStateMachine;

import java.sql.*;
import java.util.Scanner;

public class Testmain {
    public static void main(String[] args) {
        UiStateMachine ui = new UiStateMachine();
        ui.dispatch();
    }
}
