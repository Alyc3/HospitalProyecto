package hospital.repositorio.json;

import hospital.codificacion.json.CodificacionEntidad;
import hospital.modelo.Cuenta;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
import java.text.ParseException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import hospital.repositorio.RepositorioCuenta;

public class RepositorioCuentaJSON implements RepositorioCuenta {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioCuentaJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaCuentaRepositoryFile);
    }

    /**
     * Método que recibe una excepción y detalla cual fue el error ocurrido
     * @param e
     * @throws ModeloException 
     */
    private void detallarExcepcion(Exception e) throws ModeloException {
        if (IOException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error al intentar escribir o acceder, a información o contenido de los "
                    + "archivos del repositorio CuentaJSONRepository",
                    e,
                    ErrorType.ErrorAccesoDatos
            );
        } else if (ParseException.class.isInstance(e)) {
            throw new ModeloException(
                    "No se pueden extraer datos del repositorio CuentaJSONRepository, "
                    + "porque el contenido de sus archivos no cumple correctamente con el "
                    + "formato json",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (IncompatibleTypeForJSONFieldException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error de incompatibilidad entre los tipos de datos guardados "
                    + "en CuentaJSONRepository y los definidos por la aplicación",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (ModeloException.class.isInstance(e)) {
            throw (ModeloException) e;
        } else {
            throw new ModeloException(e.getMessage(), e, ErrorType.ErrorDesconocido);
        }
    }

    /**
     * Método que valida que la cuenta tenda id, usuario, clave e id de Persona definidos
     * @param cuenta
     * @param validarId
     * @throws ModeloException 
     */
    private void validar(Cuenta cuenta, boolean validarId) throws ModeloException {
        if (validarId) {
            if (cuenta.getId() == null) {
                throw new ModeloException(
                        "El campo id de la entidad Cuenta no puede ser null",
                        ErrorType.ErrorValorNuloNoPermitido
                );
            }
        }
        if (cuenta.getUsuario() == null || cuenta.getUsuario().equals("")) {
            throw new ModeloException(
                    "El campo usuario de la entidad Cuenta no puede ser null, ni vacío",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (cuenta.getClave() == null || cuenta.getClave().equals("")) {
            throw new ModeloException(
                    "El campo clave de la entidad Cuenta no puede ser null, ni vacío",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (cuenta.getPersona() == null || cuenta.getPersona().getId() == null) {
            throw new ModeloException(
                    "El campo idPersona de la entidad Cuenta no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    @Override
    public Cuenta crear(Cuenta cuenta) throws ModeloException {
        try {
            validar(cuenta, false);
            JSONArray objects = almacenamiento.obtenerTodo();
            Cuenta[] cuentas = CodificacionEntidad.decodificarCuentas(objects);

            Integer maxUsedId = 0;
            for (int i = 0; i < cuentas.length; i++) {
                maxUsedId = Math.max(maxUsedId, cuentas[i].getId());

                if (cuenta.getUsuario().equals(cuentas[i].getUsuario())) {
                    throw new ModeloException(
                            "Ya existe una cuenta con ese nombre de usuario",
                            ErrorType.ErrorRestriccionUnicidad
                    );
                }
                if (cuenta.getPersona().getId().equals(cuentas[i].getPersona().getId())) {
                    throw new ModeloException(
                            "Ya existe otra cuenta asociada a la misma persona",
                            ErrorType.ErrorRestriccionUnicidad
                    );
                }
            }

            cuenta.setId(maxUsedId + 1);

            JSONObject object = CodificacionEntidad.codificarCuenta(cuenta);
            almacenamiento.guardarUno(object);
            return cuenta;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public Cuenta actualizar(Cuenta cuenta) throws ModeloException {
        try {
            validar(cuenta, true);
            JSONArray objects = almacenamiento.obtenerTodo();
            Cuenta[] cuentas = CodificacionEntidad.decodificarCuentas(objects);
            for (int i = 0; i < cuentas.length; i++) {
                if (cuenta.getId() == cuentas[i].getId()) {
                    if (!cuenta.getPersona().getId().equals(cuentas[i].getPersona().getId())) {
                        throw new ModeloException(
                                "La persona asociada a la cuenta no puede ser modificada",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }
                    if (!cuenta.getUsuario().equals(cuentas[i].getUsuario())) {
                        Set<String> setUsuarios = new HashSet<String>();
                        for (int j = 0; j < cuentas.length; j++) {
                            setUsuarios.add(cuentas[j].getUsuario());
                        }
                        if (setUsuarios.contains(cuenta.getUsuario())) {
                            throw new ModeloException(
                                    "El nombre de usuario ya lo tiene otra cuenta",
                                    ErrorType.ErrorOperacionNoValida
                            );
                        }
                    }
                    cuentas[i] = cuenta;
                    objects = CodificacionEntidad.codificarCuentas(cuentas);
                    almacenamiento.limpiarYGuardarVarios(objects);
                    return cuenta;
                }
            }
            throw new ModeloException("La cuenta no existe", ErrorType.ErrorObjetoNoEncontrado);
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public Cuenta encontrarPorId(Integer id) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            Cuenta[] cuentas = CodificacionEntidad.decodificarCuentas(objects);
            for (int i = 0; i < cuentas.length; i++) {
                if (id == cuentas[i].getId()) {
                    return cuentas[i];
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public Cuenta encontrarPorUsuario(String usuario) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            Cuenta[] cuentas = CodificacionEntidad.decodificarCuentas(objects);

            for (int i = 0; i < cuentas.length; i++) {
                if (usuario.equals(cuentas[i].getUsuario())) {
                    return cuentas[i];
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }
}
