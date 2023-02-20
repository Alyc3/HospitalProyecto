package hospital.controlador.json.repositorio.json;

import hospital.controlador.json.CodificacionEntidad;
import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.json.AlmacenamientoJSON;
import hospital.controlador.json.repositorio.json.RepositorioJSONUtilidades;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RepositorioConsultaMedicaJSON implements RepositorioConsultaMedica {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioConsultaMedicaJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaConsultaMedicaRepositoryFile);
    }

    /**
     * Valida los campos que no deben ser nulos (fecha, hora y numConsultorio)
     *
     * @param consultaMedica
     * @param validateId
     * @throws ModeloException
     */
    private void validar(ConsultaMedica consultaMedica, boolean validateId) throws ModeloException {
        if (validateId) {
            if (consultaMedica.getId() == null) {
                throw new ModeloException(
                        "El campo id de la ConsultaMedica no puede ser null",
                        ErrorType.ErrorValorNuloNoPermitido
                );
            }
        }
        if (consultaMedica.getFecha() == null) {
            throw new ModeloException(
                    "El campo fecha de la ConsultaMedica no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (consultaMedica.getHora() == null) {
            throw new ModeloException(
                    "El campo hora de la ConsultaMedica no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (consultaMedica.getNumConsultorio() == null) {
            throw new ModeloException(
                    "El campo numConsultorio de la ConsultaMedica no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    /**
     * Método que permite crear una nueva consulta medica, verificando que no
     * exista una con el mismo numero de consultorio, fecha y hora
     *
     * @param consultaMedica
     * @return
     * @throws ModeloException
     */
    @Override
    public ConsultaMedica crear(ConsultaMedica consultaMedica) throws ModeloException {
        try {
            validar(consultaMedica, false);
            JSONArray objects = almacenamiento.obtenerTodo();
            ConsultaMedica[] consultasMedicas = CodificacionEntidad.decodificarConsultasMedicas(objects);
            Integer maxUsedId = 0;
            for (int i = 0; i < consultasMedicas.length; i++) {
                maxUsedId = Math.max(maxUsedId, consultasMedicas[i].getId());

                /*if(consultaMedica.getNumConsultorio() == consultasMedicas[i].getNumConsultorio()){
					if(consultaMedica.getFecha().equals(consultasMedicas[i].getFecha())){
						if(consultaMedica.getHora().equals(consultasMedicas[i].getHora())){
							throw new ApplicationException(
								"Ya existe una consulta médica que coincide en numConsultorio, fecha y hora",
								ErrorType.UNIQUENESS_CONSTRAINT_VIOLATION_ERROR
							);
						}
					}
				}*/
            }

            consultaMedica.setId(maxUsedId + 1);
            JSONObject object = CodificacionEntidad.codificarConsultaMedica(consultaMedica);
            almacenamiento.guardarUno(object);
            return consultaMedica;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioConsultaMedicaJSON");
            return null;
        }
    }

    /**
     * Método para actualizar la información de una consulta médica,
     * encontrandola por medio de su id, si no la encuentra retorna null
     *
     * @param consultaMedica
     * @return
     * @throws ModeloException
     */
    @Override
    public ConsultaMedica actualizar(ConsultaMedica consultaMedica) throws ModeloException {
        try {
            validar(consultaMedica, true);
            JSONArray objects = almacenamiento.obtenerTodo();
            ConsultaMedica[] consultasMedicas = CodificacionEntidad.decodificarConsultasMedicas(objects);
            for (int i = 0; i < consultasMedicas.length; i++) {
                if (consultaMedica.getId() == consultasMedicas[i].getId()) {
                    // Verifica que no exista otra consulta médica que coincida en el
                    // numConsultorio, fecha y hora (pero que tenga un id diferente).
                    /*for(int j=0; j<consultasMedicas.length; j++){
						if(consultaMedica.getId() != consultasMedicas[j].getId()){
							if(consultaMedica.getNumConsultorio() == consultasMedicas[j].getNumConsultorio()){
								if(consultaMedica.getFecha().equals(consultasMedicas[j].getFecha())){
									if(consultaMedica.getHora().equals(consultasMedicas[j].getHora())){
										throw new ApplicationException(
											"Ya existe otra consulta médica que coincide en numConsultorio, fecha y hora",
											ErrorType.UNIQUENESS_CONSTRAINT_VIOLATION_ERROR
										);
									}
								}
							}
						}
					}*/
                    consultasMedicas[i] = consultaMedica;
                    objects = CodificacionEntidad.codificarConsultasMedicas(consultasMedicas);
                    almacenamiento.limpiarYGuardarVarios(objects);
                    return consultaMedica;
                }
            }
            return null;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioConsultaMedicaJSON");
            return null;
        }
    }

    /**
     * Método que busca todas las consultas médicas cuyo historial nmédico sea
     * igual al buscado
     *
     * @param idHistorialMedico
     * @return
     * @throws ModeloException
     */
    @Override
    public ListaEnlazada<ConsultaMedica> encontrarTodosPorIdHistorialMedico(Integer idHistorialMedico) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            ConsultaMedica[] consultasMedicas = CodificacionEntidad.decodificarConsultasMedicas(objects);
            ListaEnlazada<ConsultaMedica> listConsultasMedicas = new ListaEnlazada<ConsultaMedica>();
            for (int i = 0; i < consultasMedicas.length; i++) {
                if (consultasMedicas[i].getHistorialMedico() != null) {
                    if (idHistorialMedico == consultasMedicas[i].getHistorialMedico().getId()) {
                        listConsultasMedicas.insertarFinal(consultasMedicas[i]);
                    }
                }
            }
            return listConsultasMedicas;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioConsultaMedicaJSON");
            return null;
        }
    }

    /**
     * Método para buscar todas las consultas médicas que tengan su estado en
     * PENDIENTE
     *
     * @return
     * @throws ModeloException
     */
    @Override
    public ListaEnlazada<ConsultaMedica> encontrarTodosConEstadoPendiente() throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            ConsultaMedica[] consultasMedicas = CodificacionEntidad.decodificarConsultasMedicas(objects);
            ListaEnlazada<ConsultaMedica> listConsultasMedicas = new ListaEnlazada<ConsultaMedica>();
            for (int i = 0; i < consultasMedicas.length; i++) {
                if (consultasMedicas[i].getEstado() != null) {
                    if (consultasMedicas[i].getEstado().equals("PENDIENTE")) {
                        listConsultasMedicas.insertarFinal(consultasMedicas[i]);
                    }
                }
            }
            return listConsultasMedicas;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioConsultaMedicaJSON");
            return null;
        }
    }

    /**
     * Método para encontrar una consulta médica por medio de su id, si no la
     * encuentra retorna null
     *
     * @param id
     * @return
     * @throws ModeloException
     */
    @Override
    public ConsultaMedica encontrarPorId(Integer id) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            ConsultaMedica[] consultasMedicas = CodificacionEntidad.decodificarConsultasMedicas(objects);
            for (int i = 0; i < consultasMedicas.length; i++) {
                if (id == consultasMedicas[i].getId()) {
                    return consultasMedicas[i];
                }
            }
            return null;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioConsultaMedicaJSON");
            return null;
        }
    }

    /**
     * Método para encontrar todas la consulta médica asociada a un historial
     * médico y que además tengan su estado PENDIENTE
     *
     * @param idHistorialMedico
     * @return
     * @throws ModeloException
     */
    @Override
    public ConsultaMedica encontrarPorIdHistorialMedicoConEstadoPendiente(Integer idHistorialMedico) throws ModeloException {
        ListaEnlazada<ConsultaMedica> listConsultasMedicas = encontrarTodosPorIdHistorialMedico(idHistorialMedico);
        ConsultaMedica[] consultasMedicas = new ConsultaMedica[listConsultasMedicas.size()];
        listConsultasMedicas.toArray(consultasMedicas);
        for (int i = 0; i < consultasMedicas.length; i++) {
            if (consultasMedicas[i].getEstado() != null && consultasMedicas[i].getEstado().equals("PENDIENTE")) {
                return consultasMedicas[i];
            }
        }
        return null;
    }
}
