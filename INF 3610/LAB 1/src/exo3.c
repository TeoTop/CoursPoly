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
* File : exo3.c
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

#define TANK_A_PRIO   		6				 // Defining Priority of each task
#define TANK_B_PRIO   		5
#define CONTROLLER_PRIO     4
#define MIXER_PRIO			7

#define TANK_A_ID    		1                // Defining Id of each task
#define TANK_B_ID    		2
#define CONTROLLER_ID    	3
#define MIXER_ID    		4

/*
*********************************************************************************************************
*                                             VARIABLES
*********************************************************************************************************
*/

OS_STK           tankAStk[TASK_STK_SIZE];	//Stack of each task
OS_STK           tankBStk[TASK_STK_SIZE];	
OS_STK           mixerStk[TASK_STK_SIZE];
OS_STK           controllerStk[TASK_STK_SIZE];

/*
*********************************************************************************************************
*                                           SHARED  VARIABLES
*********************************************************************************************************
*/
OS_EVENT* sem_controller_to_tank_A;
OS_EVENT* sem_tank_A_to_tank_B;
OS_EVENT* sem_tank_B_to_tank_A;
OS_EVENT* sem_tank_A_to_mixer;
OS_EVENT* sem_mixer_empty;
/*
*********************************************************************************************************
*                                         FUNCTION PROTOTYPES
*********************************************************************************************************
*/
void    tank_A(void *data);
void    tank_B(void *data);
void    mixer(void *data);
void    controller(void *data);
void    errMsg(INT8U err, char* errMSg);

/*
*********************************************************************************************************
*                                                  MAIN
*********************************************************************************************************
*/

void main(void)
{
	UBYTE err;

	OSInit();

	sem_controller_to_tank_A = OSSemCreate(0);

	sem_tank_A_to_tank_B = OSSemCreate(0);

	sem_tank_B_to_tank_A = OSSemCreate(0);

	sem_tank_A_to_mixer = OSSemCreate(0);

	sem_mixer_empty = OSSemCreate(1);

	OSTaskCreate(controller, (void *)0, &controllerStk[TASK_STK_SIZE - 1], CONTROLLER_PRIO);

	OSTaskCreate(tank_A, (void *)0, &tankAStk[TASK_STK_SIZE - 1], TANK_A_PRIO);

	OSTaskCreate(tank_B, (void *)0, &tankBStk[TASK_STK_SIZE - 1], TANK_B_PRIO);

	OSTaskCreate(mixer, (void *)0, &mixerStk[TASK_STK_SIZE - 1], MIXER_PRIO);

	OSStart();
	
    return;
}

/*
*********************************************************************************************************
*                                            TASK FUNCTIONS
*********************************************************************************************************
*/


void tank_A(void* data)
{
	INT8U err;
	int startTime = 0;
	int orderNumber = 1;
	printf("TACHE TANK A @ %d : DEBUT.\n", OSTimeGet() - startTime);
	while (1)
	{
		OSSemPend(sem_controller_to_tank_A, 0, &err);
		OSSemPend(sem_mixer_empty, 0, &err);
		err = OSSemPost(sem_tank_A_to_tank_B);
		
		errMsg(err, "Error while trying to access sem_controller_to_tanks");

		printf("TACHE TANK A COMMANDE #%d @ %d : Déversage composant A.\n", orderNumber, OSTimeGet() - startTime);
		OSTimeDly(300);
		printf("TACHE TANK A COMMANDE #%d @ %d : fin déversage composant A.\n", orderNumber, OSTimeGet() - startTime);

		OSSemPend(sem_tank_B_to_tank_A, 0, &err);

		printf("TACHE TANK A COMMANDE #%d @ %d : Demande de démarrage malaxeur.\n", orderNumber, OSTimeGet() - startTime);
		
		err = OSSemPost(sem_tank_A_to_mixer);
		
		orderNumber++;
	}

}

void tank_B(void* data)
{
	INT8U err;
	int startTime = 0;
	int orderNumber = 1;
	printf("TACHE TANK B @ %d : DEBUT. \n", OSTimeGet() - startTime);
	while (1)
	{
		OSSemPend(sem_tank_A_to_tank_B, 0, &err);
		
		printf("TACHE TANK B COMMANDE #%d @ %d : Déversage composant B.\n", orderNumber, OSTimeGet() - startTime);
		OSTimeDly(700);
		printf("TACHE TANK B COMMANDE #%d @ %d : Fin déversage composant B.\n", orderNumber, OSTimeGet() - startTime);
		
		err = OSSemPost(sem_tank_B_to_tank_A);
		
		orderNumber++;
	}
}
void mixer(void* data)
{
	INT8U err;
	int startTime = 0;
	int orderNumber = 1;
	printf("TACHE MIXER @ %d : DEBUT. \n", OSTimeGet() - startTime);
	while (1)
	{
		OSSemPend(sem_tank_A_to_mixer, 0, &err);

		printf("TACHE MIXER COMMANDE #%d @ %d : Début malaxage.\n", orderNumber, OSTimeGet() - startTime);
		OSTimeDly(1000);
		printf("TACHE MIXER COMMANDE #%d @ %d : Fin malaxage et déversage.\n", orderNumber, OSTimeGet() - startTime);
		
		err = OSSemPost(sem_mixer_empty);

		orderNumber++;

	}
}
void controller(void* data)
{
	INT8U err;
	int startTime = 0;
	int randomTime = 0;
	printf("TACHE CONTROLLER @ %d : DEBUT. \n", OSTimeGet() - startTime);
	for(int i = 1 ; i < 11; i++)
	{	
		randomTime = (rand() % 10 + 5)*100;
		OSTimeDly(randomTime);

		printf("TACHE CONTROLLER @ %d : COMMANDE #%d. \n", OSTimeGet() - startTime, i);
		
		err = OSSemPost(sem_controller_to_tank_A);
		errMsg(err, "Error while trying to post sem_controller_to_tanks");

	}
}
void errMsg(INT8U err, char* errMsg)
{
	if (err != OS_ERR_NONE)
	{
		printf(errMsg);
		exit(1);
	}
}


