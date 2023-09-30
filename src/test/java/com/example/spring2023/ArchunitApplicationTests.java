package com.example.spring2023;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchunitApplicationTests {

    private JavaClasses importedClasses;

    @BeforeEach
    public void setup() {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.example.spring2023");
    }
    @Test
    void domainClassesShouldOnlyBeAccessedByApplication() {
        classes()
                .that().resideInAPackage("..domain..")
                .should().onlyBeAccessed().byAnyPackage("..domain..", "..application..")
                .check(importedClasses);
    }

    @Test
    void applicationClassesShouldOnlyBeAccessedByInfrastructure() {
        classes()
                .that().resideInAPackage("..application..")
                .should().onlyBeAccessed().byAnyPackage("..application..", "..infrastructure..")
                .check(importedClasses);
    }
}