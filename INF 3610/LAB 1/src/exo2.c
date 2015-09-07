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
* File : exo2.c
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
#define TASK2_PRIO   		7
#define TASK3_PRIO   		8
#define TASK4_PRIO   		5

#define TASK1_ID    		1                // Defining Id of each task
#define TASK2_ID    		2
#define TASK3_ID    		3
#define TASK4_ID    		3

/*
*********************************************************************************************************
*                                             VARIABLES
*********************************************************************************************************
*/

OS_STK           Task1Stk[TASK_STK_SIZE];	//Stack of each task
OS_STK           Task2Stk[TASK_STK_SIZE];	
OS_STK           Task3Stk[TASK_STK_SIZE];
OS_STK           Task4Stk[TASK_STK_SIZE];

/*
*********************************************************************************************************
*                                           SHARED  VARIABLES
*********************************************************************************************************
*/

char printerBuffer[50] ;
int bufferPosition = -1;
OS_EVENT* mutex;

/*
*********************************************************************************************************
*                                         FUNCTION PROTOTYPES
*********************************************************************************************************
*/
void    Task1(void *data);
void    Task2(void *data);
void    Task3(void *data);
void    Task4(void *data);

/*
*********************************************************************************************************
*                                                  MAIN
*********************************************************************************************************
*/

void main(void)
{
	OSInit();

	INT8U err;

	mutex = OSMutexCreate(4, &err);

	OSTaskCreate(Task1, (void *)0, &Task1Stk[TASK_STK_SIZE-1], TASK1_PRIO);

	OSTaskCreate(Task2, (void *)0, &Task2Stk[TASK_STK_SIZE-1], TASK2_PRIO);

	OSTaskCreate(Task3, (void *)0, &Task3Stk[TASK_STK_SIZE -1], TASK3_PRIO);

	OSTaskCreate(Task4, (void *)0, &Task4Stk[TASK_STK_SIZE -1], TASK4_PRIO);

	OSStart();

}

/*
*********************************************************************************************************
*                                            TASK FUNCTIONS
*********************************************************************************************************
*/

void Task1(void* data)
{
	INT8U err = OS_ERR_NONE;
	char msg_to_send[] = "I am task 1";
	int startTime = 0;
	while (1)
	{
		printf("TACHE 1 @ %d : Debut tache 1.\n", OSTimeGet() - startTime);

		OSMutexPend(mutex, 0, &err);
		for (int i = 0; i < 11; i++)
		{
			printf("T1 : i = %d \n", i);
			++bufferPosition;
			printerBuffer[bufferPosition] = msg_to_send[i];
			OSTimeDly(3);
		}
		err = OSMutexPost(mutex);
		printf("TACHE 1 @ %d : Suspension.\n", OSTimeGet() - startTime);
		OSTaskSuspend(TASK1_PRIO);
	}
}

void Task2(void* data)
{
	INT8U err = OS_ERR_NONE;
	char msg_to_send[] = " and i am task 2";
	int startTime = 0;
	while (1)
	{
		printf("TACHE 2 @ %d : Debut tache 2. \n", OSTimeGet() - startTime);
		OSMutexPend(mutex, 0, &err);
		for (int i = 0; i < 16; i++)
		{
			printf("T2 : i = %d \n", i);
			++bufferPosition;
			printerBuffer[bufferPosition] = msg_to_send[i];
			OSTimeDly(2);
		}
		err = OSMutexPost(mutex);
		printf("TACHE 2 @ %d : Suspension. \n", OSTimeGet() - startTime);
		OSTaskSuspend(TASK2_PRIO);
	}
}

void Task3(void* data)
{
	int startTime = 0;
	while (1)
	{
		printf("TACHE 3 @ %d : Debut tache 3. \n", OSTimeGet() - startTime);
		OSTimeDly(70);
		printf("TACHE 3 @ %d : Fin du delay . \n", OSTimeGet() - startTime);
		++bufferPosition;
		printerBuffer[bufferPosition] = '\0';
		printf(printerBuffer);
		bufferPosition = -1;
		OSTaskSuspend(TASK3_PRIO);
	}
}



void Task4(void* data)
{
	while (1)
	{
		printf("End of task creation ! \n");
		OSTimeDly(10000);
	}
}

