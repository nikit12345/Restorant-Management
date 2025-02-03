package com.restorent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.restorent.controller.CustomerController;
import com.restorent.dto.CategoryDto;
import com.restorent.services.AdminService;
import com.restorent.services.CustomerService;


@WebMvcTest(controllers = CustomerController.class)
@ExtendWith(MockitoExtension.class)
public class YourControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private CustomerService customerService;
	
	@Mock
	private AdminService adminService;
	
	@InjectMocks
	private CustomerController customerController;
	
	@BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
	@Test
	public void testGetAllCategory() throws Exception {
		
		 MockMultipartFile file = new MockMultipartFile("img", "test-image.jpg",
	                MediaType.IMAGE_JPEG_VALUE, "Test Image Content".getBytes());
	        when(adminService.postCategory(any(CategoryDto.class))).thenReturn(new CategoryDto());

		 
	        mockMvc.perform(multipart("/your-endpoint")
	                .file(file)
	                .param("id", "1")
	                .param("name", "Category Name")
	                .param("description", "Category Description")
	                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaWtpdGFAZ21haWwuY29tIiwiaWF0IjoxNzI0NjU2NDU2LCJleHAiOjE3MjQ2NjAwNTZ9.FUoCa3cwZH6zJDBkVOsGR3l_FrFUth8u7JiQKG3A7lo")
	                .contentType(MediaType.MULTIPART_FORM_DATA))
	                .andExpect(status().isOk());}
}
