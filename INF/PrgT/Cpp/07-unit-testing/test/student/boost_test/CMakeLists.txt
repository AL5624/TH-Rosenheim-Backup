project(boost_test_student_tests)

set (Boost_USE_STATIC_LIBS OFF)


include_directories (${Boost_INCLUDE_DIRS})

add_executable(${PROJECT_NAME} student_tests.cpp)
target_link_libraries(${PROJECT_NAME} lib_student Boost::unit_test_framework)

set(Labels student-boost.test)
add_test(
        NAME
        Boost.Test-Student
        COMMAND
        ${PROJECT_NAME}
        --log_format=JUNIT
        --log_sink=${PROJECT_NAME}.xml
)
set_tests_properties(
        Boost.Test-Student
        PROPERTIES
        LABELS "${Labels}"
)