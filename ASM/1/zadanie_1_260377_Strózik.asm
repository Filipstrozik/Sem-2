.data
	naglowek:	.asciiz "Kalkulator! 260377\n"
	pytanieOperacja:	.asciiz "ktora operacja? \n1 - dodawanie, 2 - odejmowanie 3 - mnozenie, 4 - dzielenie.\n"
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
	
	slti $t4, $t0,5 			#wybor operacji >=5
	beq $t4, 0, zlaopr			#jezeli zla opcja operacji to skocz na czesc koncowa
	
	slt $t4, $zero, $t0			#wybor operacji <=0
	beq $t4, 0, zlaopr			#jezeli zla opcja operacji to skocz na czesc koncowa

	
	beq $t3, 1, skok			#jezeli chcemy dzialac na wyniku poprzedniej operacji to omijamy wczytywanie no1
	
	jal displayNo1				#wysietl zapytanie o No1
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t1, $v0				#przechowaj dane z v0 to t1
	
	skok:	
	
	jal displayNo2				#wysietl zapytanie o No2
	
	li $v0, 5				#wczytaj dane input do v0
	syscall
	
	move $t2, $v0				#przechowaj dane z v0 to t2
	
	
	beq $t0, 1, dodawanie

	beq $t0, 2, odejmowanie

	beq $t0, 3, mnozenie
	
	beq $t0, 4, dzielenie
	

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
