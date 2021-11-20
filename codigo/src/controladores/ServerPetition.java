package controladores;

/**
 *
 * @author Esteban
 */
public class ServerPetition {
    private AdminPedidos adminPedidos;
    private AdminProductos andminProductos;
    
    public ServerPetition(){
        adminPedidos = new AdminPedidos();
        andminProductos = new AdminProductos();
    }
}
