project(virtual_methods_and_polymorphism_tests)

add_executable(${PROJECT_NAME}
        vehicle_tests.cpp
)
target_link_libraries(
        ${PROJECT_NAME}
        lib_virtual_methods_and_polymorphism
        gtest
        gtest_main
)

set(Labels virtual-methods-and-polymorphism-tests)
gtest_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES LABELS "${Labels}"
        XML_OUTPUT_DIR reports
)