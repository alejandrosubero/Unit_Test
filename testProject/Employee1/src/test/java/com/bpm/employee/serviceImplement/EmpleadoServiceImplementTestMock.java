
package com.bpm.employee.serviceImplement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import com.bpm.employee.entitys.Empleado;
import com.bpm.employee.repository.EmpleadoRepository;
import com.bpm.employee.mapper.EmpleadoMapper;


import java.util.Date;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceImplementTestMock {


	@Mock
	private Date fechaEgreso;
	@Mock
	private Empleado empleadoEntity;

    @Mock
    private Empleado empre;


    @InjectMocks
    private EmpleadoServiceImplement empleadoServiceImplement;

    @Mock
    private EmpleadoRepository empleadorepository;

    @Mock
    private EmpleadoMapper mapper;


    @Mock
    private Empleado employee;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveEmpleado() {

        Mockito.when(empleadorepository.save(employee)).thenReturn(employee);
        boolean saveEmpleadoResult = empleadoServiceImplement.saveEmpleado(employee);

        Mockito.verify(empleadorepository, Mockito.times(1)).save(employee);
        Assertions.assertThat(saveEmpleadoResult).isTrue();

    }


    @Test
    public void testUpdateEmpleado() {
        empre = employee;
        Mockito.when(empleadorepository.save(empre)).thenReturn(empre);
        boolean updateEmpleadoResult = empleadoServiceImplement.updateEmpleado(employee);
        Mockito.verify(empleadorepository, Mockito.times(1)).save(empre);
        Assertions.assertThat(updateEmpleadoResult).isTrue();
    }



	@Test
	public void testFindByFechaEgreso(){
 	Optional<Empleado> optionalEmpleado = Optional.of(empleadoEntity);
Mockito.when(empleadorepository.findByFechaEgreso(fechaEgreso)).thenReturn(optionalEmpleado);
	Empleado findByFechaEgresoResult = empleadoServiceImplement.findByFechaEgreso(fechaEgreso);

		
	Assertions.assertThat(findByFechaEgresoResult).isNotNull();
}



//	@Test
//	public void testFindByIdxxxx(){
//        Long id = 1L;
//        empleadoEntity.setId(id);
// 	Optional<Empleado> optionalEmpleado = Optional.of(empleadoEntity);
//
//        Mockito.when(empleadorepository.findById(Mockito.any(Long.class))).thenReturn(optionalEmpleado);
////	Mockito.when(empleadorepository.findById(optionalEmpleado.get().getId())).thenReturn(optionalEmpleado);
//	Empleado findByIdResult = empleadoServiceImplement.findById(id);
//
//	Mockito.verify(empleadorepository, Mockito.times(1)).findById(id);
//	Assertions.assertThat(findByIdResult).isNotNull();
//}

	@Test
	public void testFindById(){
 	Long id = 1L;
empleadoEntity.setId(id);

Optional<Empleado> optionalEmpleado = Optional.of(empleadoEntity);
	Mockito.when(empleadorepository.findById(Mockito.any(Long.class))).thenReturn(optionalEmpleado);
	Empleado findByIdResult = empleadoServiceImplement.findById(id);

	Mockito.verify(empleadorepository, Mockito.times(1)).findById(id);	
	Assertions.assertThat(findByIdResult).isNotNull();
}



	@Test
	public void testFindByTotalHorasFeriadoYear(){
 	Optional<Empleado> optionalEmpleado = Optional.of(empleadoEntity);
	Mockito.when(empleadorepository.findByTotalHorasFeriadoYear(5l)).thenReturn(optionalEmpleado);
	Empleado findByTotalHorasFeriadoYearResult = empleadoServiceImplement.findByTotalHorasFeriadoYear(5l);
	Assertions.assertThat(findByTotalHorasFeriadoYearResult).isNotNull();
}
}
