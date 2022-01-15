# This is the complete program to run the West High's Disruptive Innovation FRC Robot.

## Setup

- Install WPILib tools/VSCode [v2022.1.1](https://github.com/wpilibsuite/allwpilib/releases/tag/v2022.1.1)
  - Setup instructions for [VSCode](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html)
  - Installing [3rd party libraries](https://docs.wpilib.org/en/stable/docs/software/vscode-overview/3rd-party-libraries.html#rd-party-libraries)
  - REV sparkmax [online installation](https://docs.revrobotics.com/sparkmax/software-resources/spark-max-api-information#online-installation)
- [FRC Game Tools](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/frc-game-tools.html)



## Building and Deploying Robot Code

Build the robot code using the WPILib tools.
- From VSCode, hit `Ctrl+Shift+P`, type `WPILib: Build Robot Code` and then press `Enter`.
- The build will take a few minutes to complete.
  - Overview from [WPILib Docs](https://docs.wpilib.org/en/stable/docs/software/vscode-overview/deploying-robot-code.html)

Deploy the robot code to the robot using WPILib tools.
- From VSCode, hit `Ctrl+Shift+P`, type `WPILib: Deploy Robot Code` and then press `Enter`.
- The deploy will take a few minutes - if successful, we will see a “Build Successful” message and the RioLog will open with the console output from the robot program as it runs.



## Running the Robot

TODO: add docs


## Debugging
Note: you must be connected to the robot to debug.

Viewing Console Output and Logs
- Use the [roboRIO console viewer](https://docs.wpilib.org/en/stable/docs/software/vscode-overview/viewing-console-output.html).
- You can alternatively use the RioLog by clicking `Ctrl+Shift+P`, then `WPILib: Start RioLog`

Debugging Robot Code
- From VSCode, hit `Ctrl+Shift+P`, type `WPILib: Debug Robot Code` and then press `Enter`.
- You can add breakpoints to examine lines in the code during execution by clicking next to the line number in the code editor.