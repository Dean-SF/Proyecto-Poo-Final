package controladores;

/**
 * Enumeracion que permite saber que tipo de operacion se va a realizar en el
 * serverPetition segun el tipo de peticion.
 * @author Esteban
 */
public enum TPeticion {
    AGREGAR_PROD, ELIMINAR_PROD, CONSULTAR_PROD, MODIFICAR_PROD, AGREGAR_PED, 
    ELIMINAR_PED, CONSULTAR_PED, APAGAR, ESTA_ENCENDIDO, CONSULTAR_LISTA_PROD,
    CONSULTAR_LISTA_PED,LISTA_TOP,LISTA_SIN_PEDIR,CANTIDADES,CANTIDAD_PEDIDOS,
    INGRESAR,MODEXPRESS,MODRECOGER,EXPRESS,RECOGER;
}
