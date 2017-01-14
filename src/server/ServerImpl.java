package server;

public class Server {

    public static void main(String[] args){

        try {
            
        }


        // Indico la IP donde se deben exportar los objetos remotos
        System.setProperty("java.rmi.server.hostname", LOCALHOST);

        // Indico desde donde se pueden descargar las clases que necesiten los
        // clientes: ClienteJoven, ClienteBanco, etc...
        System.setProperty("java.rmi.server.codebase", "http://" + LOCALHOST + "/twitter/");

        // Añado la política de seguridad que permita leer las clases del codebase
        System.setProperty("java.security.policy", "twitter.policy");

    }

}
