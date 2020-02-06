package com.zamoiski;

import com.zamoiski.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/webapp")
public class WebAppController {

    private EmployeeServiceWeb employeeServiceWeb;
    private DepartmentServiceWeb departmentServiceWeb;

    public WebAppController(EmployeeServiceWeb employeeServiceWeb, DepartmentServiceWeb departmentServiceWeb){
        this.employeeServiceWeb = employeeServiceWeb;
        this.departmentServiceWeb = departmentServiceWeb;
    }

    @RequestMapping(value = "/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping(value = "/employees")
    public ModelAndView getEmployees() {
        ModelAndView modelAndView = new ModelAndView("viewEmployees");
        modelAndView.addObject("employees", employeeServiceWeb.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/employees/update/{id}")
    public ModelAndView updateEmployee(@PathVariable("id") Long id,Employee employee) {
        ModelAndView modelAndView = new ModelAndView("addupdateEmployee");
        modelAndView.addObject("employee_id",id);
        modelAndView.addObject("departments",departmentServiceWeb.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/employees/add")
    public ModelAndView addEmployee(Employee employee) {
        ModelAndView modelAndView = new ModelAndView("addupdateEmployee");
        modelAndView.addObject("departments",departmentServiceWeb.findAll());
        return modelAndView;
    }

    @PostMapping(value = "/employees/put/{id}")
    public ModelAndView updateEmployee(@PathVariable("id") Long id,@RequestParam(value = "departmentID") String departmentID,@Valid Employee employee, BindingResult bindingResult) {
        employee.setDepartment(departmentServiceWeb.findById(Long.parseLong(departmentID)));
        if (bindingResult.hasErrors()) {
           return updateEmployee(id,employee);
        }


        employeeServiceWeb.update(id, employee);
        return getEmployees();
    }

    @PostMapping(value = "/employees/put")
    public ModelAndView addEmployee(@RequestParam(value = "departmentID") String departmentID,@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("addupdateEmployee");
        }
        employee.setDepartment(departmentServiceWeb.findById(Long.parseLong(departmentID)));
        employeeServiceWeb.update(null, employee);
        return getEmployees();
    }

    @GetMapping(value = "/employees/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") Long id) {
        employeeServiceWeb.deleteById(id);
        return getEmployees();
    }
}
