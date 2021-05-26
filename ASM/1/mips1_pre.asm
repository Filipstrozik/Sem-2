.data
	naglowek:	.asciiz "Kalkulator! \n"
	pytanieOperacja:	.asciiz "ktora operacja? \n"
	msg3:	.asciiz "Dodawanie \n"
	msg4:	.asciiz "Odejmowanie \n"
	age: 	.word	23
	number1: .word 20
	number2: .word 8

.text
	#glowna funkcja od ktorej sie zaczyna program
	main:
	
	jal displayNaglowek
	
	while:
	jal displayOperacja
	
	#wczytaj dane input do v0
	li $v0, 5
	syscall
	
	#przechowaj dane
	move $t0, $v0
	
	li $v0, 5
	syscall
	
	move $t1, $v0
	
	li $v0, 5
	syscall
	
	move $t2, $v0
	
	
	beq  $t0, 1, dodaj
	j dalej1
	dodaj: 
		jal dodawanie
	dalej1: 
	beq  $t0, 2, odejmij
	j dalej2
	odejmij:
		jal odejmowanie
	dalej2:
	
	#wyswietl dane wczytana
	li $v0, 1
	move $a0, $t0
	syscall
	
	
	
	#ta linia to sygna³ zakonczenia programu.
	li $v0, 10
	syscall



	

	displayNaglowek:
		li $v0, 4
		la $a0, naglowek
		syscall
			
		jr $ra
		
		
	displayOperacja:
		li $v0, 4
		la $a0, pytanieOperacja
		syscall
			
		jr $ra
		
		

	dodawanie:
		li $v0, 4
		la $a0, msg3
		syscall	
		
		jr $ra
		
	odejmowanie:
		li $v0, 4
		la $a0, msg4
		syscall	
		
		jr $ra

#	li $v0, 4
#	la $a0, msg1
#	syscall
#	la $a0, msg2
#	syscall
#	li $v0, 1
#	lw $a0, age
#	syscall

#	add $a0, $zero, $t2


#	lw $t0, number1($zero)
#	lw $t1, number2($zero)
##	mul $t2, $t0, $t1
##	li $v0, 1
#
#	move $a0, $t2
#
#	syscall
