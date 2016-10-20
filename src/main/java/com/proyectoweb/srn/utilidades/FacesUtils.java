/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.utilidades;

//import com.icesoft.faces.context.effects.JavascriptContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andresrevelo
 */
public class FacesUtils {

    public static ServletContext getServletContext() {
        return (ServletContext) getCurrentInstance().getExternalContext().getContext();
    }

    public static FacesContext getCurrentInstance() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc;
    }

    public static void addErrorMessageConDll(String msg, String dll) {
        addErrorMessage(null, msg, dll, FacesMessage.SEVERITY_ERROR);
    }

    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }

    public static void addErrorMessage(String clientId, String msg, String detalle, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(severidad, msg, detalle));
    }

    public static void addErrorMessage(String clientId, String msg) {
        addErrorMessage(clientId, msg, null, FacesMessage.SEVERITY_ERROR);
    }

    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }

    public static String getPath() {
        return getServletContext().getRealPath("");
    }

    public static Object getAttribute(ActionEvent evt, String nombAttr) {
        return evt.getComponent().getAttributes().get(nombAttr);
    }

//    public static void alert(String msg) {
//        JavascriptContext.addJavascriptCall(getCurrentInstance(), "alert(\"" + msg + "\");");
//    }
    public static Object getParam(String param) {
        Map<String, String> params = getCurrentInstance().getExternalContext().getRequestParameterMap();

        return params.get(param);
    }

    public static Object getManagedBean(String beanName) {
        return getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    private static ValueBinding getValueBinding(String el) {
        return getApplication().createValueBinding(el);
    }

    public static Flash flashScope() {
        return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }

    public static boolean isCamposValidos(Object object, String formulario) {
        Object obj = new Object();
        boolean retorna = true;

        Method[] metodoNew = object.getClass().getDeclaredMethods();
        for (Method method2 : metodoNew) {

            if (((method2.getName().startsWith("get")) && (method2.getName().endsWith("collection")))
                    && (!method2.getName().equals("getId"))) {
                try {
                    obj = method2.invoke(object, new Object[0]);

                    if (null == obj || obj.toString().isEmpty() || obj.equals("")) {
                        String campo = method2.getName().replaceAll("get", "");
                        FacesUtils.addErrorMessage(formulario + ":" + campo.toLowerCase() + "", "Codigo no puede ser nulo ");
                        retorna = false;
                    }
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(FacesUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(FacesUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(FacesUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return retorna;
    }

    public static List<SelectItem> getSelectItems(List<?> entities, int posicionUno, int posicionDos, String nombreDescripcion) {

        int size = entities.size();
        int i = 0;
        List<SelectItem> items = new ArrayList();

        try {
            items.add(new SelectItem("0", "--Seleccione--"));
            for (Object x : entities) {
                Object[] datos = new Object[2];
                datos = dato(x, nombreDescripcion);
                if (datos.length > 0) {
                    if (datos[posicionUno] instanceof Integer) {
                        items.add(new SelectItem((Integer) datos[posicionUno], (String) datos[posicionDos]));
                    } else {
                        items.add(new SelectItem((String) datos[posicionUno], (String) datos[posicionDos]));
                    }

                }
            }
        } catch (Exception e) {
            System.out.print("errror " + e.getMessage());
        }
        return items;
    }

    private static Object[] dato(Object o, String nombreDescripcion) {
        Object[] retorna = new Object[2];

        Object obj = new Object();
        Method[] metodoNew = o.getClass().getDeclaredMethods();

        try {
            for (Method method2 : metodoNew) {
                if (method2.getName().equals("getId")) {
                    if (method2.invoke(o, new Object[0]) instanceof Integer) {
                        retorna[0] = (Integer) method2.invoke(o, new Object[0]);
                    } else {
                        retorna[0] = (String) method2.invoke(o, new Object[0]);
                    }

                } else if ((method2.getName().equals(nombreDescripcion))) {
                    retorna[1] = (String) method2.invoke(o, new Object[0]);
                }
            }
        } catch (Exception e) {
            System.out.print("errror " + e.getMessage());
        }

        return retorna;
    }

    public static Object getValue(String expr) {
        Object bc = null;
        //  ValueBinding vb = getApplication().createValueBinding(expr);

        //    FacesContext context = FacesContext.getCurrentInstance();
        try {
            bc = getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), expr, Object.class);
        } catch (Exception e) {
            System.out.println("getValue");
        }

        return bc;
        //  return bc.
    }

    protected static FacesContext getFacesContext() {

        return FacesContext.getCurrentInstance();

    }

    public static byte[] bytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("No se pudo completar la lectura del archivo " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }

    /**
     * Add information message.
     *
     * @param msg the information message
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Add information message to a specific client.
     *
     * @param clientId the client id
     * @param msg the information message
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    public static HttpSession getSession() {
        HttpSession session;
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session;
    }

    public static boolean isNotNull(String cadena) {
        return cadena != null && !cadena.equals("") && !cadena.isEmpty();
    }

    public static void controlLog(String level, Object ex) {
        switch(level){
            case "ALL": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.ALL, null, ex);
                break;
            case "CONFIG": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.CONFIG, null, ex);
                break;
            case "FINE": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.FINE, null, ex);
                break;
            case "FINEST": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.FINEST, null, ex);
                break;
            case "INFO": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.INFO, null, ex);
                break;
            case "OFF": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.OFF, null, ex);
                break;
            case "SEVERE": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            case "WARNING": 
                Logger.getLogger(FacesUtils.class.getName()).log(Level.WARNING, null, ex);
                break;
        }
    }
}
