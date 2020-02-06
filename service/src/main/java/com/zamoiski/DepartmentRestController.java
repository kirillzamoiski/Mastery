package com.zamoiski;

import com.zamoiski.model.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentRestController {
    private DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping("/departments")
    public List<Department> findAll(){
        return departmentService.findAll();
    }

    @GetMapping("/departments/{departmentId}")
    public Department findById(@PathVariable Long departmentId){
        return departmentService.findById(departmentId);
    }

    @PostMapping("/departments")
    public Department addEmployee(@RequestBody Department department){
        departmentService.save(department);
        return department;
    }

    @PutMapping("/departments")
    public Department updateEmployee(@RequestBody Department department){
        departmentService.save(department);
        return department;
    }

    @DeleteMapping("/departments/{departmentsId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long departmentsId){
        departmentService.deleteById(departmentsId);
        return ResponseEntity.ok().build();
    }
}
