.data
	naglowek:	.asciiz "Kalkulator! 260377\n"
	pytanieOperacja:	.asciiz "ktora operacja? \n1 - dodawanie, 2 - odejmowanie 3 - mnozenie, 4 - dzielenie. 5 - potegowanie. 6 - silnia.\n"
	pytanieNaWynik:	.asciiz "Czy chcesz dzialac na poprzednim wyniku TAK/NIE (1/0) \n"
	msg3:	.asciiz "Wynik Dodawania =  \n"
	msg4:	.asciiz "Wynik Odejmowania =  \n"
	msg5: 	.asciiz "Wynik Mnozenia =  \n"
	msg6: 	.asciiz "Wynik Dzielenia = \n"
	msg7: 	.asciiz "\nCzy chcesz kontynuowac? TAK/NIE (1/0) \n"
	msgNo1: .asciiz "Wprowadz pierwsza liczbe \n"
	msgNo2: .asciiz "Wprowadz druga liczbe \n"

.text						# $t0 - operacja arytmetyczna
						# $t1 - pierwsza liczba / wynik operacji
						# $t2 - druga liczba operacji aryt.
						# $t3 - flaga czy chcemy wykonac dzialanie na poprzenim wyniku
						# $t4 - flaga czy chcemy wykonac inne dzialanie aryt/ ew blad wprowadzeniaoperacji
	
	main:					#glowna funkcja od ktorej sie zaczyna program
	
	lw $s0, 0($zero)

	jal displayNaglowek			#wysiwetl "Kalkulator!"
	

	while:					#glowna petla dzialania programu, petla czy kontunujemy dzialanie
	
	beq $t0, 0, skoknawynik			#w t0 mamy ostatnia operacje, jezeli 0<t0<5 to byla dobra operacja
						#za pierwszym razem musimy ominac pytanie o dzialanie na wyniku, poniewaz
						#ten wynik jeszce nie istneje
	
	jal displayOperacjaNaWyniku
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t3, $v0				#przenies dane z input do t3
	
	skoknawynik:
	
	jal displayOperacja
	

	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t0, $v0				#przechowaj dane z v0 to t0
	
	tgei  $t0, 7
	tlti  $t0, 1
	
	slti $t4, $t0,7 			#wybor operacji >=5
	beq $t4, 0, zlaopr			#jezeli zla opcja operacji to skocz na czesc koncowa
	
	slt $t4, $zero, $t0			#wybor operacji <=0
	beq $t4, 0, zlaopr			#jezeli zla opcja operacji to skocz na czesc koncowa

	
	beq $t3, 1, skok			#jezeli chcemy dzialac na wyniku poprzedniej operacji to omijamy wczytywanie no1
	powtorzPierwsza:
	li $k0, 0
	jal displayNo1				#wysietl zapytanie o No1
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	bne $k0,0, powtorzPierwsza
	
	move $t1, $v0				#przechowaj dane z v0 to t1
	
	beq $t0, 6, skipsecond
	skok:	

	

	li $k0, 0
	jal displayNo2				#wysietl zapytanie o No2
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	bne $k0,0, skok
	move $t2, $v0				#przechowaj dane z v0 to t2
	
	skipsecond:
	
	beq $t0, 1, dodawanie

	beq $t0, 2, odejmowanie

	beq $t0, 3, mnozenie
	
	beq $t0, 4, dzielenie 
	
	beq $t0, 5, potegowanie
	
	beq $t0, 6, silnia
	

	displayNaglowek: 	#wyswietlenie naglowka programu
		li $v0, 4
		la $a0, naglowek
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
		
		
	displayOperacja:
		li $v0, 4
		la $a0, pytanieOperacja
		syscall
			
		jr $ra		#powrot do lini po wywolaniu skoku
		
			
	displayOperacjaNaWyniku:
		li $v0, 4
		la $a0, pytanieNaWynik
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
		
	displayNo1:
		li $v0, 4
		la $a0, msgNo1
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku
		
	displayNo2:
		li $v0, 4
		la $a0, msgNo2
		syscall
		jr $ra		#powrot do lini po wywolaniu skoku

	dodawanie:
		li $v0, 4
		la $a0, msg3
		syscall	
		
		add $t1, $t1, $t2
		
		j koniec	#skok do czesci koncowej
		
	odejmowanie:
		li $v0, 4
		la $a0, msg4
		syscall	
		
		sub $t1, $t1, $t2
		
		j koniec	#skok do czesci koncowej
		
	mnozenie:
		li $v0, 4
		la $a0, msg5
		syscall	
		mul $t1, $t1, $t2
		
		j koniec	#skok do czesci koncowej
	
	dzielenie:
		li $v0, 4
		la $a0, msg6
		syscall
		div $t1, $t1, $t2
		
		j koniec	#skok do czesci koncowej
		
	potegowanie: 
		
		beq $t2,0,zero
		li $t4,1
		pow:
			mul $t4, $t4, $t1
			subi $t2, $t2, 1
			bge $t2, 1 ,pow
  		 
  
  		la $t1,($t4)
		j koniec	#skok do czesci koncowej
		
		zero:
		li $t1, 1
		j koniec
		
	silnia:

		li $t4,1
		fac:
			mul $t4, $t4, $t1
			subi  $t1, $t1, 1
			bge $t1,1,fac
			
		la $t1,($t4)
		j koniec	#skok do czesci koncowej
		
		
	koniec:
		
		move $a0, $t1	#program wypisuje wynik aktualnego dzialania
		li $v0, 1
		syscall
			
		zlaopr:		

		li $v0, 4	#czy chcesz kontynuowac?
		la $a0, msg7
		syscall
		
		li $v0, 5
		syscall
		move $t4, $v0	# w tym momencie nadpisujemy rejestr
		beq $t4, 1, while
		
				#ta linia to sygna³ zakonczenia programu.
		li $v0, 10
		syscall
		
.kdata
		
syscallErr: .asciiz "SYSCALL ERROR\n\n" 
dividebyzeroE: .asciiz "DIVIDE BY ZERO\n\n" 
overflowE: .asciiz "OVERFLOW EXEPTION\n\n"
BAD_ADDRESS_EXCEPTION:  .asciiz "BAD ADRESS EXCEPTION\n\n"
TRAP_EXCEPTION:  .asciiz "ZLE WPROWADZENIE WYBORU \n\n"

.ktext 0x80000180  
   
__kernel_entry_point:

# Get value in cause register and copy it to $k0.
	
mfc0 $k0, $13   
	
# Mask all but the exception code (bits 2 - 6) to zero.
	
andi $k1, $k0, 0x00007c  
	
# Shift two bits to the right to get the exception code in a comparable form
	
srl  $k1, $k1, 2
	
# Now $k0 = value of cause register
#     $k1 = exception code 

__exception:

# Branch on value of the the exception code in $k1.
# (overflow exception has the code 12)
 beq $k1, 4, __bad_address_exception
 beq $k1, 8, __syscall_exception
 beq $k1, 9, __dividebyzero_exception
 beq $k1, 13, __trap_exception
 beq $k1, 12, __overflow_exception
 
 j __resume_from_exception

__syscall_exception:

#  Use the MARS built-in system call 4 (print string) to print error messsage.
	
 li $v0, 4
 la $a0, syscallErr
 syscall
 
j __resume_from_exception


__dividebyzero_exception:

#  Use the MARS built-in system call 4 (print string) to print error messsage.
	
 li $v0, 4
 la $a0, dividebyzeroE
 syscall
 
j __resume_from_exception
 	
 

__overflow_exception:

#  Use the MARS built-in system call 4 (print string) to print error messsage.
	
 li $v0, 4
 la $a0, overflowE
 syscall
 
j __resume_from_exception

 __bad_address_exception:

  	#  Use the MARS built-in system call 4 (print string) to print error messsage.
	
	li $v0, 4
	la $a0, BAD_ADDRESS_EXCEPTION
	syscall
 
 j __resume_from_exception
 
 
 __trap_exception:

  	#  Use the MARS built-in system call 4 (print string) to print error messsage.
	
	li $v0, 4
	la $a0, TRAP_EXCEPTION
	syscall
 
 j __resume_from_exception
 	
__resume_from_exception: 
	
# When an exception or interrupt occurs, the value of the program counter 
# ($pc) of the user level program is automatically stored in the exception 
# program counter (ECP), the $14 in Coprocessor 0. 

# Get value of EPC (Address of instruction causing the exception).
       
mfc0 $k0, $14 # not needed for eret
    
# Use the eret (Exception RETurn) instruction to set the program counter
# (PC) to the value saved in the ECP register (register 14 in coporcessor 0).
#mfc0 $k0, $12
#addi $k0,$k0,-2
#mtc0 $k0, $12
addi $k0, $k0, 4 # TODO: Uncomment this instruction 
mtc0 $k0, $14

eret # Look at the value of $14 in Coprocessor 0 before single stepping.
