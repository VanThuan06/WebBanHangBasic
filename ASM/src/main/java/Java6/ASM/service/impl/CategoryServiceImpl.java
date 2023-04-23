package Java6.ASM.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.ASM.Entities.Category;
import Java6.ASM.Repositories.CategoryReopository;
import Java6.ASM.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryReopository categoryReopository;

    @Override
    public List<Category> findAll() {
        return categoryReopository.findAll();
    }
    
}
