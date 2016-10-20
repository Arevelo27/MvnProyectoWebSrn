/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class UtilidadesSeguridad {

    /*
    *configName_persistence: nombre de configuración de la persistencia
    */
    public static final String NOMBRE_PERSISTENCIA = "WebSrnPU";

    /**
     * * Clase con mÃ©todos estÃ¡ticos de cifrado *
     */

    /**
     * Encripta el texto ingresado en MD5
     *
     * @param texto
     * @return
     */
    public static String getMD5(String texto) {
        return hash(texto, "MD5");
    }

    /**
     *
     */
    public static void getControlSession() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            // Usar el contexto de JSF para invalidar la sesiÃ³n,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

            // RedirecciÃ³n de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallarÃ¡.
            // Sin embargo, como ya estÃ¡ fuera del ciclo de vida
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/endsession.jsp");
//                    this.pasarGarbageCollector();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Codifica el texto ingresado en Base64
     *
     * @param texto
     * @return Valor Codificado
     */
    public static String encodeBase64(String texto) {
        return encode(texto.getBytes());
    }

    /**
     * Decodifica un texto en Base64 y retorna el valor decodificado
     *
     * @param texto
     * @return
     */
    public static String decodeBase64(String texto) {
        return new String(decode(texto));
    }

    private static final char[] HEXADECIMAL = {'0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String hash(String stringToHash, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int low = (int) (bytes[i] & 0x0f);
                int high = (int) ((bytes[i] & 0xf0) >> 4);
                sb.append(HEXADECIMAL[high]);
                sb.append(HEXADECIMAL[low]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //exception handling goes here
            return null;
        }
    }

    //BASE 64
    /**
     * Decodes BASE64 encoded string.
     *
     * @param encoded BASE64 string to decode
     * @return decoded data
     */
    public static byte[] decode(String encoded) {
        int i;
        byte output[] = new byte[3];
        int state;
        final String ILLEGAL_STRING = "Illegal BASE64 string";

        ByteArrayOutputStream data = new ByteArrayOutputStream(encoded.length());

        state = 1;
        for (i = 0; i < encoded.length(); i++) {
            byte c;
            {
                char alpha = encoded.charAt(i);
                if (Character.isWhitespace(alpha)) {
                    continue;
                }

                if ((alpha >= 'A') && (alpha <= 'Z')) {
                    c = (byte) (alpha - 'A');
                } else if ((alpha >= 'a') && (alpha <= 'z')) {
                    c = (byte) (26 + (alpha - 'a'));
                } else if ((alpha >= '0') && (alpha <= '9')) {
                    c = (byte) (52 + (alpha - '0'));
                } else if (alpha == '+') {
                    c = 62;
                } else if (alpha == '/') {
                    c = 63;
                } else if (alpha == '=') {
                    break; // end
                } else {
                    throw new IllegalArgumentException(ILLEGAL_STRING); // error
                }
            }

            switch (state) {
                case 1:
                    output[0] = (byte) (c << 2);
                    break;
                case 2:
                    output[0] |= (byte) (c >>> 4);
                    output[1] = (byte) ((c & 0x0F) << 4);
                    break;
                case 3:
                    output[1] |= (byte) (c >>> 2);
                    output[2] = (byte) ((c & 0x03) << 6);
                    break;
                case 4:
                    output[2] |= c;
                    data.write(output, 0, output.length);
                    break;
            }
            state = (state < 4 ? state + 1 : 1);
        } // for

        if (i < encoded.length()) /* then '=' found, but the end of string */ {
            switch (state) {
                case 3:
                    data.write(output, 0, 1);
                    if ((encoded.charAt(i) == '=') && (encoded.charAt(i + 1) == '=')) {
                        return data.toByteArray();
                    } else {
                        throw new IllegalArgumentException(ILLEGAL_STRING);
                    }

                case 4:
                    data.write(output, 0, 2);
                    if (encoded.charAt(i) == '=') {
                        return data.toByteArray();
                    } else {
                        throw new IllegalArgumentException(ILLEGAL_STRING);
                    }

                default:
                    throw new IllegalArgumentException(ILLEGAL_STRING);
            }
        } else { // end of string
            if (state == 1) {
                return data.toByteArray();
            } else {
                throw new IllegalArgumentException(ILLEGAL_STRING);
            }
        }

    } // decode

    private final static String BASE64
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    /**
     * Encodes binary data by BASE64 method.
     *
     * @param data binary data to encode
     * @return BASE64 encoded data
     */
    private static String encode(byte[] data) {

        char output[] = new char[4];
        int state = 1;
        int restbits = 0;
        int chunks = 0;

        StringBuffer encoded = new StringBuffer();

        for (int i = 0; i < data.length; i++) {

            int ic = (data[i] >= 0 ? data[i] : (data[i] & 0x7F) + 128);
            switch (state) {
                case 1:
                    output[0] = BASE64.charAt(ic >>> 2);
                    restbits = ic & 0x03;
                    break;
                case 2:
                    output[1] = BASE64.charAt((restbits << 4) | (ic >>> 4));
                    restbits = ic & 0x0F;
                    break;
                case 3:
                    output[2] = BASE64.charAt((restbits << 2) | (ic >>> 6));
                    output[3] = BASE64.charAt(ic & 0x3F);
                    encoded.append(output);

                    // keep no more then 76 character per line
                    chunks++;
                    if ((chunks % 19) == 0) {
                        encoded.append("\r\n");
                    }
                    break;
            }
            state = (state < 3 ? state + 1 : 1);
        } // for

        /* finalize */
        switch (state) {
            case 2:
                output[1] = BASE64.charAt((restbits << 4));
                output[2] = output[3] = '=';
                encoded.append(output);
                break;
            case 3:
                output[2] = BASE64.charAt((restbits << 2));
                output[3] = '=';
                encoded.append(output);
                break;
        }

        return encoded.toString();
    } // encode()

}
