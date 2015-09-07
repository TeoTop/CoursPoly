/*
*********************************************************************************************************
*                                                 uC/OS-II
*                                          The Real-Time Kernel
*                                               PORT Windows
*
*
*            			Arnaud Desaulty, Ecole Polytechnique de Montreal, Qc, CANADA
*                                                  08/2015
*
* File : exo1.c
* 
*********************************************************************************************************
*/

// Main include of µC-II
#include "includes.h"
/*
*********************************************************************************************************
*                                              CONSTANTS
*********************************************************************************************************
*/

#define TASK_STK_SIZE       16384            // Size of each task's stacks (# of WORDs)

#define TASK1_PRIO   		6				 // Defining Priority of each task
#define TASK2_PRIO   		5
#define TASK3_PRIO   		7
#define TASK4_PRIO   		8
#define TASK5_PRIO   		9
#define TASK6_PRIO   		4

#define TASK1_ID    		1                // Defining Id of each task
#define TASK2_ID    		2
#define TASK3_ID    		3
#define TASK4_ID    		3
#define TASK5_ID    		3
#define TASK6_ID    		3

/*
*********************************************************************************************************
*                                          SHARED  VARIABLES
*********************************************************************************************************
*/

OS_STK           Task1Stk[TASK_STK_SIZE];	//Stack of each task
OS_STK           Task2Stk[TASK_STK_SIZE];	
OS_STK           Task3Stk[TASK_STK_SIZE];
OS_STK           Task4Stk[TASK_STK_SIZE];
OS_STK           Task5Stk[TASK_STK_SIZE];
OS_STK           Task6Stk[TASK_STK_SIZE];


/*
*********************************************************************************************************
*                                         FUNCTION PROTOTYPES
*********************************************************************************************************
*/
void    Task1(void *data);
void    Task2(void *data);
void    Task3(void *data);
void    Task4(void *data);
void    Task5(void *data);
void    Task6(void *data);

/*
*********************************************************************************************************
*                                                  MAIN
*********************************************************************************************************
*/

void main(void)
{
	UBYTE err;

	OSInit();

	OSTaskCreate(Task1, (void *)0, &Task1Stk[TASK_STK_SIZE-1], TASK1_PRIO);

	OSTaskCreate(Task2, (void *)0, &Task2Stk[TASK_STK_SIZE-1], TASK2_PRIO);

	OSTaskCreate(Task3, (void *)0, &Task3Stk[TASK_STK_SIZE-1], TASK3_PRIO);

	OSTaskCreate(Task4, (void *)0, &Task4Stk[TASK_STK_SIZE-1], TASK4_PRIO);

	OSTaskCreate(Task5, (void *)0, &Task5Stk[TASK_STK_SIZE-1], TASK5_PRIO);

	OSTaskCreate(Task6, (void *)0, &Task6Stk[TASK_STK_SIZE-1], TASK6_PRIO);

	OSStart();
	
    return;
}

/*
*********************************************************************************************************
*                                            TASK FUNCTIONS
*********************************************************************************************************
*/


void Task1(void* data)
{
	while(1)
	{
		printf("are an \n");
		OSTimeDly(10000);
	}
}

void Task2(void* data)
{
	while (1)
	{
		printf("Task priorities \n");
		OSTimeDly(10000);
	}
}

void Task3(void* data)
{
	while (1)
	{
		printf("important \n");
		OSTimeDly(10000);
	}
}

void Task4(void* data)
{
	while (1)
	{
		printf("feature \n");
		OSTimeDly(10000);
	}
}

void Task5(void* data)
{
	while (1)
	{
		printf("of MicroC-II ! \n");
		OSTimeDly(10000);
	}
}



void Task6(void* data)
{
	while (1)
	{
		printf("End of task creation ! \n");
		OSTimeDly(10000);
	}
}

