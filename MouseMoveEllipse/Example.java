public class Example {
    public static void main(String[] args) throws Exception {
        Thread.sleep(1000); // wait 1 second

        // makes an astroid (kinda)
        MouseMoveEllipse.init(50, 50, "S0.25 I1 R");
        MouseMoveEllipse.init(-50, 50, "S0.50 I1 R");
        MouseMoveEllipse.init(-50, -50, "S0.75 I1 R");
        MouseMoveEllipse.init(50, -50, "S1 I1 R");

        Thread.sleep(1000); // wait 1 second

        // makes a circle
        MouseMoveEllipse.init(50, 50, "S0.35 I0 R");
        MouseMoveEllipse.init(-50, 50, "S0.35 I0 R");
        MouseMoveEllipse.init(-50, -50, "S0.35 I0 R");
        MouseMoveEllipse.init(50, -50, "S0.35 I0 R");
    }
}
