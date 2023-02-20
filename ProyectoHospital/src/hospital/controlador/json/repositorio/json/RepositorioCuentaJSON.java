package hospital.controlador.json.repositorio.json;

import hospital.controlador.json.CodificacionEntidad;
import hospital.modelo.Cuenta;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import hospital.controlador.json.repositorio.RepositorioCuenta;

public class RepositorioCuentaJSON implements RepositorioCuenta {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioCuentaJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaCuentaRepositoryFile);
    }

    /**
     * Método que valida que la cuenta tenda id, usuario e id de Persona
     * definidos
     *
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
        if (cuenta.getPersona() == null || cuenta.getPersona().getId() == null) {
            throw new ModeloException(
                    "El campo idPersona de la entidad Cuenta no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    /**
     * Método para crear una nueva cuenta, comprobando que no exista una con el mismo usuario o asociada a la misma persona
     * @param cuenta
     * @return
     * @throws ModeloException 
     */
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
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioCuentaJSON");
            return null;
        }
    }

    /**
     * Método que permite actualizar una cuenta existente, utilizando su id para buscarla
     * @param cuenta
     * @return
     * @throws ModeloException 
     */
    @Override
    public Cuenta actualizar(Cuenta cuenta) throws ModeloException {
        try {
            validar(cuenta, true);
            JSONArray objects = almacenamiento.obtenerTodo();
            Cuenta[] cuentas = CodificacionEntidad.decodificarCuentas(objects);
            for (int i = 0; i < cuentas.length; i++) {
                if (cuenta.getId() == cuentas[i].getId()) {
                    if (cuenta.getPersona().getId() != cuentas[i].getPersona().getId()) {
                        throw new ModeloException(
                                "La persona asociada a la cuenta no puede ser modificada",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }
                    for (int j = 0; j < cuentas.length; j++) {
                        if (cuenta.getId() != cuentas[j].getId()) {
                            if (cuenta.getUsuario().equals(cuentas[j].getUsuario())) {
                                throw new ModeloException(
                                        "Ya existe otra cuenta con ese usuario",
                                        ErrorType.ErrorRestriccionUnicidad
                                );
                            }
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
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioCuentaJSON");
            return null;
        }
    }

    /**
     * Método que permite encontrar una cuenta cuyo id coincida con el id enviado
     * @param id
     * @return
     * @throws ModeloException 
     */
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
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioCuentaJSON");
            return null;
        }
    }

    /**
     * Método que permite buscar una cuenta cuyo usuario sea igual al enviado
     * @param usuario
     * @return
     * @throws ModeloException 
     */
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
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioCuentaJSON");
            return null;
        }
    }
}
