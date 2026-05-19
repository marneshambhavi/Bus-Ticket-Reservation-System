import buspass.register;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class TestGuiInsert {
    public static void main(String[] args) {
        try {
            // Null parentFrame is fine for testing if it's only used for showLoginScreen
            register regPanel = new register(null);
            
            // Find all components and populate them using reflection or just accessing public methods (if any)
            // Since fields are private, let's use reflection to inject values
            setFieldValue(regPanel, "tname", "Test User");
            setFieldValue(regPanel, "email", "test@test.com");
            setFieldValue(regPanel, "userfield", "testuser2");
            setFieldValue(regPanel, "pwdfield", "testpwd");
            setFieldValue(regPanel, "tage", "12-12-2000");
            setFieldValue(regPanel, "genderfield", "Male");
            
            // tadd is JTextArea
            java.lang.reflect.Field addressField = register.class.getDeclaredField("tadd");
            addressField.setAccessible(true);
            JTextArea tadd = (JTextArea) addressField.get(regPanel);
            tadd.setText("123 Test Address");
            
            // Click submit
            java.lang.reflect.Field submitField = register.class.getDeclaredField("sub");
            submitField.setAccessible(true);
            JButton submitBtn = (JButton) submitField.get(regPanel);
            
            System.out.println("Invoking submit button action...");
            // Trigger action listener
            for (java.awt.event.ActionListener al : submitBtn.getActionListeners()) {
                al.actionPerformed(new ActionEvent(submitBtn, ActionEvent.ACTION_PERFORMED, "Submit"));
            }
            
            System.out.println("Action performed. Check database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void setFieldValue(Object obj, String fieldName, String value) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        JTextField tf = (JTextField) field.get(obj);
        tf.setText(value);
    }
}
