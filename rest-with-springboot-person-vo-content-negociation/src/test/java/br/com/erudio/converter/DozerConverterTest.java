package br.com.erudio.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.serialization.converter.DozerConverter;

public class DozerConverterTest {
	
	MockPerson inputObjetc;
	
	@Before
	public void setUp() {
		inputObjetc = new MockPerson();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObjetc.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0l), output.getId());
		Assert.assertEquals("Fist Name Teste0", output.getFirstName());
		Assert.assertEquals("Last Name Teste0", output.getLastName());
		Assert.assertEquals("Addres Teste0", output.getAddress());
		Assert.assertEquals("Masculino", output.getGender());
	}
	
	@Test
	public void parserEntityListToVOListTest() {
		List<PersonVO> outputList = DozerConverter.parseListObjects(inputObjetc.mockEntityList(), PersonVO.class); 
		PersonVO outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("Fist Name Teste0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Teste0", outputZero.getLastName());
		Assert.assertEquals("Addres Teste0", outputZero.getAddress());
		Assert.assertEquals("Masculino", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("Fist Name Teste7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Teste7", outputSeven.getLastName());
		Assert.assertEquals("Addres Teste7", outputSeven.getAddress());
		Assert.assertEquals("Feminio", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("Fist Name Teste12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Teste12", outputTwelve.getLastName());
		Assert.assertEquals("Addres Teste12", outputTwelve.getAddress());
		Assert.assertEquals("Masculino", outputTwelve.getGender());
		
	}
	
	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObjetc.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("Fist Name Teste0", output.getFirstName());
		Assert.assertEquals("Last Name Teste0", output.getLastName());
		Assert.assertEquals("Addres Teste0", output.getAddress());
		Assert.assertEquals("Masculino", output.getGender());
	}
	
	@Test
	public void parserVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListObjects(inputObjetc.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("Fist Name Teste0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Teste0", outputZero.getLastName());
		Assert.assertEquals("Addres Teste0", outputZero.getAddress());
		Assert.assertEquals("Masculino", outputZero.getGender());
		
		Person outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("Fist Name Teste7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Teste7", outputSeven.getLastName());
		Assert.assertEquals("Addres Teste7", outputSeven.getAddress());
		Assert.assertEquals("Feminio", outputSeven.getGender());
		
		Person outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("Fist Name Teste12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Teste12", outputTwelve.getLastName());
		Assert.assertEquals("Addres Teste12", outputTwelve.getAddress());
		Assert.assertEquals("Masculino", outputTwelve.getGender());
		
	}
}
