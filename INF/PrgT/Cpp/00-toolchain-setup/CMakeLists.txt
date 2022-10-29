cmake_minimum_required(VERSION 3.18)
project(00_cpp_toolchain_setup)

set(CMAKE_CXX_STANDARD 14)


if(CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
    add_compile_options(-Wall -Wextra -Wdouble-promotion -Werror -pedantic)
endif()


### Unit Test Setup ###
# Workaround for running Google Test under Windows. Not to be used in a project if not specifically required
if(CMAKE_CXX_COMPILER_ID STREQUAL "MSVC")
    set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
endif()

include(GoogleTest)
add_subdirectory(googletest)
enable_testing()


### Doxygen setup ###
find_package(
        Doxygen
        OPTIONAL_COMPONENTS dot mscgen dia)

if (DOXYGEN_FOUND)

    # Exclude test directories
    # Exclude Markdown files
    set(DOXYGEN_EXCLUDE_PATTERNS
        *.md
        */googletest/*
    )

    set(DOXYGEN_GENERATE_HTML YES)
    set(DOXYGEN_HTML_OUTPUT html/)
    set(DOXYGEN_GENERATE_LATEX YES)


    set(DOXYGEN_OUTPUT_DIRECTORY "${PROJECT_BINARY_DIR}/docs")

    doxygen_add_docs(
            documentation
            WORKING_DIRECTORY ${PROJECT_SOURCE_DIR}
            COMMENT "Program documentation"
    )


    set(DOXYGEN_OUTPUT_DIRECTORY "${PROJECT_BINARY_DIR}/docs-internal")
    set(DOXYGEN_INTERNAL_DOCS YES)
    set(DOXYGEN_EXTRACT_PRIVATE YES)

    doxygen_add_docs(
            documentation-internal
            WORKING_DIRECTORY ${PROJECT_SOURCE_DIR}
            COMMENT  "Internal Program documentation"
    )
else()
    MESSAGE(STATUS "Doxygen not found. Generating documentation will be unavailable.")
endif ()


### Pandoc setup ###
find_program(
        PANDOC_EXECUTABLE
        NAMES
        pandoc
        DOC "Pandoc executable location"
)

if(PANDOC_EXECUTABLE)
    MESSAGE(STATUS "Found Pandoc executable. Enabling target: readme-pdf.")

    add_custom_target(
            readme-pdf
            COMMAND
                pandoc ${PROJECT_SOURCE_DIR}/README.md
                -s
                -V lang=de
                -V papersize:a4paper
                -V geometry:margin=2.5cm
                --pdf-engine=lualatex
                -o "00 Toolchain Setup.pdf"
            VERBATIM
            COMMENT "Generating PDF from README.md"
    )

else()
    MESSAGE(STATUS "Pandoc executable not found. Target readme-pdf will be unavailable.")
endif()

add_subdirectory(src)
add_subdirectory(test)