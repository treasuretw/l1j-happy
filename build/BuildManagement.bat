@ECHO OFF
TITLE Lineage Java Server Build Management
SETLOCAL ENABLEDELAYEDEXPANSION

REM =================================== 路径设定 =====================================
CD %~p0
CD ..

REM ================================================== 设定参数 ==================================================
SET ANT_BINARY=build\ant\bin\ant
SET BASE_PATH=..

REM ================================================== 显示功能选项 ==================================================
MODE CON COLS=80 LINES=26
:init
SET ARGS=
CLS
ECHO ============================== L1J 建置管理系统 ==============================
ECHO.                                                                 Design by Neo
ECHO.
ECHO 请选择要使用的功能：
ECHO.
ECHO  1. 一般建置
ECHO     完整的错误讯息资讯, 开发时较易找出错误原因.
ECHO.
ECHO  2. 最小建置 (无除错资讯)
ECHO     最小化编译后的核心程序大小, 便于网路传输.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO. 0. 结束本程式
ECHO.
ECHO.
SET /P ARGS=请输入编号来选择功能： 
CLS
IF "%ARGS%"=="" GOTO ERR_NO_ARGS
IF %ARGS%==1 GOTO opt1
IF %ARGS%==2 GOTO opt2
IF %ARGS%==0 GOTO exit
GOTO ERR_NO_ARGS

REM ================================================== 功能选项 ==================================================
:opt1
START %ANT_BINARY% all
GOTO init

:opt2
START %ANT_BINARY% mini
GOTO init

REM ================================================== 错误处理 ==================================================
:ERR_NO_ARGS
CLS
ECHO 没有这个选项 %ARGS%
ECHO.
PAUSE
GOTO init

REM ================================================== 结束程式 ==================================================
:exit
EXIT
