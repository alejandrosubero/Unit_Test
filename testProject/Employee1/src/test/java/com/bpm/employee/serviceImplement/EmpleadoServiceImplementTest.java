
package com.bpm.employee.serviceImplement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;
import com.bpm.employee.serviceImplement.EmpleadoServiceImplement;
import com.bpm.employee.entitys.Empleado;
import com.bpm.employee.repository.EmpleadoRepository;
import com.bpm.employee.mapper.EmpleadoMapper;



public class EmpleadoServiceImplementTest {


	@Autowired
	private EmpleadoServiceImplement empleadoServiceImplement;




 	@Test
 	public void testUpdateEmpleado() {
  	updateEmpleado(employee); 
 }


}
