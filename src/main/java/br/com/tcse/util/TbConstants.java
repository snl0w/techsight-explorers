package br.com.tcse.util;


public class TbConstants {
    // Interface que contém as constantes para os papéis de usuário no sistema.
    // A interface é usada para agrupar as constantes, tornando o código mais organizado e consistente.
    public static interface Roles {
        // Constante para o papel de "usuário comum"
        String USER = "ROLE_USER";

        // Constante para o papel de "administrador"
        String ADMIN = "ROLE_ADMIN";
    }
}
