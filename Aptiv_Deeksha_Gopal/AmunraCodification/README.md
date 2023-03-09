
Motivation
==========

The following small tasks are derived from our day-to-day work and thus give a good insight into the work that we are doing regularly and some of the tooling that we are using.

Working on these tasks should also give an idea on whether these topics in general are interesting and challenging.

Please go through the whole document here before you start so that you have an overview of what is expected and what informations are available here. In case of any questions or problems feel free to reach out to the contacts given in the mail you received.


Expected data/returns
=====================

Based on the tasks below we expect the following data/material to be returned to us:

* One screenshot from task 3.
* One screenshot from task 8.
* A pull request in the GitHub repository that contains all the changes that were done for the below tasks.
* (optional) We are also glad to get some feedback for this way of coding challenges/interview!

Your tasks
==========

1. Create a new branch in the repository and check it out locally on your machine. In case you are not familiar with Git yet, there are multiple free online tutorials available (e.g. [here](https://www.simplilearn.com/tutorials/git-tutorial/git-tutorial-for-beginner) or [here](https://www.edureka.co/blog/git-tutorial/)).

    | REMARK: only commit/push to your branch, and not to the master branch! |
    | --- |

2. Setup the local Jenkins build-environment.

   Jenkins is a Continuous Integration (CI) and automation tool ([website](https://jenkins.io)) used for automatic compilation and testing of the sourcecode.

   There is a predefined Jenkins setup available inside the ``environment`` folder. This setup is based on [Docker](https://en.wikipedia.org/wiki/Docker_(software)) to easily create a local environment on your own machine that can be used for testing. You need to have Docker and Docker-Compose already installed on your machine to start with this task (see further below).

   First build the environment:

        cd environment
        docker-compose -f docker-compose.yaml build

   If everything was successful start the environment:

        docker-compose -f docker-compose.yaml up -d

   After some time (~ 1 minute) the local Jenkins should be up and running and you can access it via your internet browser under http://localhost:8080 (directly from your host machine, no need to set up something additionally).

3. Get the job doing something and take a screenshot (to send us later).

   The Jenkins environment already comes with an automated job (i.e. a set of small steps to automatically execute and compile and test the code).

   This job can be reached via http://localhost:8080/job/AmunraCodification/ and your branch from GitHub should directly show up there.

   Execute the job and take a screenshot of its successful run. Keep the screenshot to send it to us later (by default the job will fail, just remove the corresponding line to make it successful).

4. Add more Python unittests.

   Inside `scripts/helper/` there is a small Python script `script_helper.py` that contains a function `convert_string_to_bool()` with a first unittest in `ut/ut_scripts.py`.

   You can run the unittest(s) by executing `pytest` from the root of the repository (where also the `pytest.ini` file is located).

   The task is to add more unittests so that all possible cases and conditions are covered. This also means that you should reach a line coverage of 100% (when running `pytest` it will also print you a table with coverage details, including the line numbers that are not covered).

5. Install the xunit plugin in your local Jenkins installation.

   Jenkins can easily be extended by different plugins. One of those plugins is [xunit](https://plugins.jenkins.io/xunit/), which will display results from unittests.

   The prepared Jenkins instance doesn't come with this plugin, so you have to manually install it. It can be done via "Manage Jenkins" -> "Manage Plugins" -> "Available", and there you can search for it and then hit "Install without restart".

6. Add the xunit buildstep to publish test results from Python unittests.

   We now want to use the xunit plugin for the results from the Python unittests. The unittests are already executed inside Jenkins and will produce a file `pytestresults.xml` . You now need to add a new step inside the `Jenkinsfile` for xunit. As a reference you can use the [official reference](https://www.jenkins.io/doc/pipeline/steps/xunit/#xunit-publish-xunit-test-result-report) or [the automatic snippet generator from your local instance](http://localhost:8080/pipeline-syntax/). The report type is `JUnit`.

7. Add the correct command to build the C++ code using CMake to the Jenkinsfile.

   The folder `src` contains a small C++ Hello-World example with a [CMake](https://cmake.org/) setup. Figure out the correct commands to compile the code via CMake and add those commands also to the `Jenkinsfile`.

8. Add compilation for the C++ file `StandardDeviation.cpp`.

   The folder `src` contains a file `StandardDeviation.cpp` that is currently not considered for the build. Adjust the CMake setup so that it will produce a dedicated executable out of it and run the executable to ensure that it is working. Take a screenshot of the program execution.

9. Add compilation for the C++ file `MatrixMultiplicationFunction.cpp` and fix the error.

   Similar to task 8 you should create an executable via CMake for it. In addition the file contains a small programming mistake that prevents successful compilation. Find and fix it too.

10. Create a pull request with all your changes in the repository.

   Once you are done with all your changes commit them to your created branch in GitHub and create a Pull Request for it.

General hints
=============

* The credentials for the Jenkins instance are `admin / aptiv`.
* The Jenkins instance is already set up to connect to the GitHub repository. To verify that your changes are working you have to push them to your branch in the repository.
* Jenkins will not automatically start a build on changes, you have to trigger a scan first (via the `Scan Multibranch Pipeline Now` button). If Jenkins detects any changes it should start a new build of your branch, otherwise you can always manually start a new build.
* To stop the Jenkins instance again you have to run `docker-compose -f docker-compose.yaml down` from the `environment` folder.

Setup
=====

The scripts here should work on most machines and operating systems (Windows, Linux), but Linux is always the preferred OS (especially as all the infrastructure is based on Linux).

Thus there are multiple ways to work on these tasks:

* Windows machine + Docker Desktop + native installation of all the tools.

  This will install several tools that you probably will rarely need again on your own machine. In addition there sometimes might be issues because of Windows.

* Windows machine + WSL ([Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/)) + all tools installed inside WSL.

  WSL is an easy solution to have a native Linux inside your Windows environment (you can think of it like a virtual machine but without a nice GUI). Microsoft offers several different Linux systems (Ubuntu, Debian, openSUSE, etc.) so you can choose your preferred one (if you have one). Usually Ubuntu is the preferred solution.

  After you have set it up you can install all the necessary tools via the Linux system package manager inside your WSL distribution (e.g. `apt-get install cmake` for Ubuntu/Debian).

  To set up Docker follow [these instructions (exemplary for Ubuntu)](https://docs.docker.com/engine/install/ubuntu/).

* Windows machine + virtual machine (e.g. [VirtualBox](https://www.virtualbox.org/)).

  Set up a virtual machine on your system and install the Linux distribution of your choice. Rest is then identical to WSL or a native Linux machine.

* Native Linux machine.

  If you are already working on a native Linux machine you can just install all the tools and should know how to handle it.

To work on the tasks you need the following tools:

* [Git](https://git-scm.com/)
* [Docker](https://www.docker.com) and [Docker Compose](https://docs.docker.com/compose/)
* [Python](https://www.python.org/) + the requirements from the `requirements.txt` (install via `pip install -r requirements.txt`)
* [CMake](https://www.cmake.org/)
* C++ compiler (preferable [GCC](https://gcc.gnu.org/) (Linux package `g++`), which is the default on Linux)
