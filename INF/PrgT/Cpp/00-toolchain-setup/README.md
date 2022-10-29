# Familiarize yourself with the Toolchain Setup
The goal of this worksheet is to guide you setting up all necessary tools and packages used in this lectures excercises.

## Necessary tool installation
Please install the newest version of all software packages noted below for your OS. If you already have installed any of the tools, updated them. There are constantly security breaches and bugs detected. Using the newest version mitigates the associated risks.

### Tool packages
**Windows**  
For windows, download the software packages directly from the noted links.

**Linux / MacOS**  
For Linux and MacOS, you can use a package management tool like `apt-get` or `homebew`.
The corresponding Debian packages are noted in brackets below. For different distributions, packages with the same or similar name can be found easily.

### Basic Software Packages
All excercises use the `Git LFS` extension for versioning the binary data. It prevents inflation of the repository und slowing down check-outs. This is especially recommended for document and/or image heavy project or thesis repositories .  
Also we use the cross platform build tool `CMake` to guarantee a consistant project structure for Windows, Linux and MacOS users.

#### Package List
- [Git](https://git-scm.com/downloads/) (`git`)
- [Git LFS (Large File Storage)](https://git-lfs.github.com) (`git-lfs`) (installation option under Windows)
- [CMake](https://cmake.org/download/) (`cmake`)
- (recommended) C++-Compiler:
  * Windows: Microsoft Visual Studio Compiler (MSVC), part of the work package "C++-Desktopentwicklung mit C++" from
  [Visual Studio](https://visualstudio.microsoft.com/de/downloads/)
  * Linux: GNU-C++-Compiler (`g++`)
  * macOS: LLVM/Clang/AppleClang (`clang`)
-  Test-Framework
  * [Google Test](https://github.com/google/googletest) (`googletest`) (**primarily used here**)
  * [Catch2](https://github.com/catchorg/Catch2) (`catch2`)
  * [Boost](https://github.com/boostorg/test) (`libboost-test-dev`)


#### Packages for auto generating documantation (optional)
**Doxygen**  
- LaTeX-Distribution
  * [TeX Live](https://www.tug.org/texlive/) (`texlive-latex-extra`, part of `doxygen-latex`)
    * English language package (`texlive-lang-english`)
    * German anguage package (`texlive-lang-german`)
  * [MikTeX](https://miktex.org/download) (`miktex`)
- [Doxygen](https://www.doxygen.nl/download.html) (`doxygen`, part of `doxygen-latex`)
- [Graphviz dot](https://www.graphviz.org/download/) (`graphviz`)
- [MscGen](https://www.mcternan.me.uk/mscgen/) (`mscgen`)
- [Diagrammeditor Dia](https://wiki.gnome.org/Apps/Dia/Download) (`dia`)

**ReadME to PDF**  
- [Pandoc](https://pandoc.org/installing.html) (`pandoc`)
- [Pandoc PlantUML Filter](https://github.com/timofurrer/pandoc-plantuml-filter) (`pandoc-plantuml-filter`)

### Script for installing all Debian packages at once
```bash
BASIC_TOOLS="git git-lfs cmake g++"
DOC_TOOLS="doxygen-latex texlive-luatex texlive-lang-english texlive-lang-german graphviz mscgen dia"
README_TOOLS="pandoc pandoc-plantuml-filter"

sudo apt-get update &&
sudo apt-get install -y $BASIC_TOOLS $DOC_TOOLS $README_TOOLS
```

### Adjusting Manual Installation without Package Management Tools
It is important for CMake that all used packages are accessable from the commandline. Especially with manual installations you have to add nnvironment variables for all programs, otherwise CMake can't automatically find them.

### Possible Problems with Visual Studio (Windows)
Visual Studio under Windows tends to change its path after updating to a newer version. If you have a project on your system for a longer period of time and run an update, it can result in the path of the compiler not being resolve correctly anymore. In this case make sure your environment variables are set correctly, also delete and check-out your project new, so that the path can be set automatically. Just deleting your build folder doesn't always help.

## Installing the IDE
If you want use an IDE, it is recommended that it supports CMake natively. The following IDEs have well CMake support:

#### [**CLion**](https://www.jetbrains.com/de-de/clion/) (Windows, Linux, MacOS)   
All students of the Universiy of Applied Science Rosenheim (TH Rosenheim) get access to JetBrain programs via the [JetBrains Student Pack](https://www.jetbrains.com/de-de/community/education/#students), just register your university e-mail there.  
If you plan to use more than one JetBrain program it is recommended to install them via the [JetBrains Toolbox](https://www.jetbrains.com/de-de/toolbox-app/). On some Linux distributions the programs can also be installed via [Snap](https://snapcraft.io)

After opening a project with CLion it will ask for a CMake profile. In principle, we only need the Debug-Profil.
But you can add more profiles by clicking on the plus symbol. The difference between each profile lies in the generated code:
- Debug: for Debugging optimized Code
- Release: for execution speed optimized Code
- RelWithDebInfo: optimized Code with Debug symbols
- MinSizeRel: for memory usage optimized Code

#### [**Visual Studio**](https://visualstudio.microsoft.com/de/) Version 2017 and above (Windows, no C++ for MacOS)  
Visual Studio Community Edition is available for free or as Professional- / Enterprise-Edition with a fee. Like with CLion, all students can use the Enterprise Edition with their university account. For this you have to register ist at [Azure Dev Tools for Teaching](https://aka.ms/devtoolsforteaching). A more detailed [Instruction](https://www.th-rosenheim.de/intranet/einrichtungen/rechenzentrum/it-services/software/microsoft-produkte/) can be found on the university Intranet. For our purposes however the Community Edition is enough.

#### [**Visual Studio Code**](https://code.visualstudio.com/) with Plugins (Windows, Linux, MacOS)  
If you want to use Visual Studio Code, the following Extensions are recommended:
- [C/C++](https://marketplace.visualstudio.com/items?itemName=ms-vscode.cpptools)
- [CMake Tools](https://marketplace.visualstudio.com/items?itemName=ms-vscode.cmake-tools)
- [CMake (Syntax Highlighting)](https://marketplace.visualstudio.com/items?itemName=twxs.cmake)
- [C++ Testmate (Test adapter)](https://marketplace.visualstudio.com/items?itemName=matepek.vscode-catch2-test-adapter)  
CMake Tools uses Cmake in its own way. Don't be surprised if you can't run the targets via the console.

##### Automatic extension installation
The project root directory contains the `.vscode` folder with the `extensions.json` file.
Visual Studio Code scans this file and provides automatic installation of the listed extensions, you just have to accept the dialog after loading the project.

##### Starting the installation yourself
If Visual Studio Code doesn't provide you an automatic extension installtion, e.g. if you closed the dialog by mistake, you can start it by executing the following VS-Code command:
```
>Extensions: Show Recommended Extensions
```

## Working with the excercises

### Fork the Repositories
If you want to safe your changes on GitLab, you have to fork the repository first. For this, click on the button on the top right and choose the location you want to fork the repository to. GitLab handles the rest for you.

### Cloning the Repositories
The recommended method for cloning a repository is via the HTTPS-Link, as it bypasses most firewall related problems. Because the repository includes Git-Submodules (e.g. GoogleTest), you have to clone them as well. If not, you get an repository with missing include files. Run the following git command:
```bash
git clone --recursive <repository>
```
Within CLion or Visual Studio this process happens automatically, when you clone a repository. In Visual Studio Code you have to use the following command:
```
>Git: Clone (Recursive)
```

### Add your Code
Take a look at the provided instructions (ReadMe) and source code. Your code should be included at the parts marked with `TODO:`.
Create a commit message to push your changes on **your** GitLab-Fork. After a successful push the CI-Pipeline will started automatically.
Make sure all stages are run successfully and take a look at the generated artifacts. Try to answer the following questions:
* Where do I find the compiled program?
* What does the test report look like?
* Where do I find the generated dokuments?
* What is the difference between the normal and internal documentation?

## Understanding the CI-Pipeline
While all packages are getting installed, take some time to read through the individual pipeline components and try to understand their interactions with each other.

### .gitlab-ci.yml
This file configurates the pipeline. Take a look at it while reading through the following explanations. A more exact keyword documentation can be found [here](https://docs.gitlab.com/ee/ci/yaml/).

#### Docker Image
The keyword `image` specifies which docker image should be used. Here, image files from [Docker Hub](https://hub.docker.com), as well as your self-build image from the GitLab included Container Registry can be used. In general, the use of an Docker Hub image is recommended, as they safe your a lot of preperation work and also provide the latest version. The configuration for our specific image can be found in an extra repository within our Cpp-Subgroup.

#### Pipeline Stages
In the section under the keyword `stages` the names and execution order of the individual pipeline stages are specified. These are run in sequence from top to bottom. Also, if jobs don't depend on other jobs, they're run in parallel.

Our Pipeline contains three stages:
* build --> syntactic correctness, dependencies and compilation
* test --> Unit-Tests, semantics correctness and functions
* documentation --> documentation generation extracted from source code comments

#### Jobs
Jobs are tasks within a specific stage, which should be executed. In our project these are:
* compile_program
* google_tests
* documentation_pdf

Each job includes a number of keywords. The keyword `stage` assignes it to the stage, `script` specifies the commandline command to execute with it and `artifacts` specifies the build-artifacts to keep, with `when` specifying from which (conditional) point in time of the pipeline. In our case the artifacts are the test report of the unit tests and the documentation pdf.


## Summary
When it comes to the development of applications, especially technical applications, the automation of indivdual processes can reduce workloads and safe time. The use of a build toolchain generalizes the compilation process and makes it available cross platform, as it uses the installed tools, check dependencies und resolve them for you automatically.  
CMake provides this functionality. Also, all steps are combined under general terms like `build`, `test`, `docs`, etc. thus the definition of each indiviual step can be left out. Regardless of the compiler used, all CMake commands stay the same.

Test frameworks secure software against regression with newer versions and are also easily extendable with new test cases. A single pipeline run can check, wheather the existing code withstands the new test case or if it has to be refactored / extended to function correctly.

Documentation within the source code by comments makes the work for developers and for the final deliverable to the customer easier, as you don't have to gather all files by hand, but generate them automatically while development.

If you want to do it right, it will take some time to setup correctly, even for small projects. But in the long run it will safe you time and nerves and will encourage you to stay focused and productive with your pojects.

CMake itself is a mighty and extensive tool, which easily can fill multiple lecture units and exercise on its own. In this lecture however, we only will look at it from a birds eye perspective. As a tipp, make sure to keep your sources within scope of the target level and use relative paths, rather than absolute ones. Even if it costs you a bit more time, you safe yourself from build problems like:

```cmake
# Bad
include_directories(../<path>)
link_directories()
link_libraries()

# Better
target_include_directories(${<CMAKE_VARIABLE>}/<path>)
target_link_directories()
target_link_libraries()
```
