
#================DATA==============================================
.data
	NAGLOWEK:		.asciiz "Wprowadz rownanie.\n"
				#.align 2
	
	WYBOR:               .asciiz "\n0) Wydrukuj drzewo \n1) Wydrukuj wynik \n2) Jako notacjê wrostkow¹ \n3) Jako notacjê postfiksow¹ \n Wybierz numer opcji:"	
	
	SOUT: 			.asciiz "SOUT "
	
	FAILED_TO_CHOOSE:     .asciiz "Tu blad\n"
                        	.align 2
	FAILED_TO_PARSE:      .asciiz "Error parseando"
				.align 2
	LOADING_TREE:         .asciiz "Loading tree...\n"
				.align  2	
				
	ZAP_ROWNANIE:		.space 80
	
	TOK_MULT:             .byte   6
 	TOK_DIV:              .byte   5
  	TOK_SUM:              .byte   7
  	TOK_SUB:              .byte   8
  	TOK_EXP:              .byte   3
  	TOK_FAC:              .byte   4
  	
  	TOK_LB:			.byte 9
  	TOK_RB:			.byte 10
  	
  	TOK_ABS:		.byte 2
  	
	TOK_EQ:               .byte   14
	TOK_NUM:              .byte   0
	
	TOK_FLOAT:              .word   -10
	
	TOK_SPACE: 	      .byte   1
	
	zeroAsFloat:		.float 0.0
	
#=================MAIN==============================================
.text
main:
  	la $a0, NAGLOWEK
  	li $v0, 4
  	syscall

  	la $a0, ZAP_ROWNANIE
  	li $a1, 79
  	li $v0, 8
  	syscall

  	la $a0, WYBOR
  	li $v0, 4
  	syscall

 	li $v0, 5
  	syscall
  
 operacje:
 	beq $v0, 0, print_tree
 	
 	beq $v0, 1, print_result

	beq $v0, 2, print_infix
	
	beq $v0, 3, print_prefix
	
	j failed_to_choose
	
 print_tree:
 	jal utworz_shunting_yard 
  	j end
  	
print_result:
	jal utworz_shunting_yard
	move $a0, $v0
	jal rpn_parser
	move $a0, $v0
	
	lwc1 $f6, zeroAsFloat
	add.s $f12, $f2 ,$f6
  
	li $v0, 1
	syscall
	
	li $v0, 2
	syscall

	j end
	
print_infix:
 	li $v0, 4
 	la $a0, ZAP_ROWNANIE
 	syscall
 	j end

print_prefix:
	jal utworz_shunting_yard
	move $s0, $v0
	
l0_begin_print_prefix:
  	move $a0, $s0
  	jal size_deque

  	beq $v0, $0, l0_end_print_prefix
  
  	move $a0, $s0
  	jal peek_front_deque

  	la $t0, TOK_NUM
  	lb $t0, ($t0)
  	lb $t1, ($v0)
  	bne $t0, $t1, is_op
  	lb $a0, 1($v0)
  	addi $a0, $a0, 48		#48 = '0'
  	j l0_continue_print_prefix
	
is_op:
	la $t0, TOK_SUM
	lb $t0, ($t0)
	beq $t0, $t1, ope_sum	

	la $t0, TOK_SUB
	lb $t0, ($t0)
	beq $t0, $t1, ope_sub
	
	la $t0, TOK_ABS
	lb $t0, ($t0)
	beq $t0, $t1, ope_abs
	
	la $t0, TOK_MULT
	lb $t0, ($t0)
	beq $t0, $t1, ope_mult
	
	la $t0, TOK_DIV
	lb $t0, ($t0)
	beq $t0, $t1, ope_div
	
	la $t0, TOK_EXP
	lb $t0, ($t0)
	beq $t0, $t1, ope_exp
	
	la $t0, TOK_FAC
	lb $t0, ($t0)
	beq $t0, $t1, ope_fac
	
	la $t0, TOK_LB
	lb $t0, ($t0)
	beq $t0, $t1, ope_lb
	
	la $t0, TOK_RB
	lb $t0, ($t0)
	beq $t0, $t1, ope_rb

	la $t0, TOK_EQ
	lb $t0, ($t0)
	beq $t0, $t1, ope_eq
	
	la $t0, TOK_SPACE
	lb $t0, ($t0)
	beq $t0, $t1, ope_space
	
	
ope_sum:
	li $a0, 43		# 43 = '+'
	j l0_continue_print_prefix
	
ope_abs:
	li $a0, 124		# 124 = '|'
	j l0_continue_print_prefix

ope_sub:
	li $a0, 45		# 45 = '-'
	j l0_continue_print_prefix

ope_mult:
	li $a0, 42		# 42 = '*'
	j l0_continue_print_prefix

ope_div:
	li $a0, 47		# 47 = '/'
	j l0_continue_print_prefix

ope_exp:
	li $a0, 94		# 94 = '^'
	j l0_continue_print_prefix
	
ope_lb:
	li $a0, 40		# 40 = '('
	j l0_continue_print_prefix
	
ope_rb:
	li $a0, 41		# 41 = ')'
	j l0_continue_print_prefix
	
ope_fac:
	li $a0, 33		# 33 = '!'
	j l0_continue_print_prefix

ope_eq:
	li $a0, 61		# 61 = '='
	j l0_continue_print_prefix
	
ope_space:
	li $a0, 32		# 32 = ' '
	j l0_continue_print_prefix
	
l0_continue_print_prefix:
	li $v0, 11
	syscall
  
	move $a0, $s0
	jal pop_front_deque

	j l0_begin_print_prefix	
	
l0_end_print_prefix:
	li $v0, 11
	li $a0, 0
	syscall

	j end
	
sout:
	la $a0, SOUT
	li $v0, 4
	syscall
	jr $ra
	
	
failed_to_choose:
	la $a0, FAILED_TO_CHOOSE
	li $v0, 4
	syscall
	j end

failed_to_parse:
	la $a0, FAILED_TO_PARSE
	li $v0, 4
	syscall
	j end

end:
	li $v0, 10
	syscall
 	
 	
#=====================ALGORYTM SHUNTING YARD==========================================

utworz_shunting_yard:
	sub $sp $sp 4
	sw $ra ($sp)

	la $a0, LOADING_TREE
	li $v0, 4
	syscall
	
	jal create_deque
  	move $s0, $v0		#s0 przetrzumuje  orgina³ stosu

  	jal create_deque
  	move $s1, $v0		#s1 przetrzymuje kolejke
  	
  	la $s2, ZAP_ROWNANIE
  	lb $s3, ($s2)    	#w s3 przechowujemyaktualny byte
  	
l0_begin:
  	beq $s3, $0 , l0_end
  	
	move $a0, $s3 		#przechowujemy do a0 byte
	jal match
	beq $v0, $0, l0_continue  
	
	beq $v0, 2, rightbracket
	#warunek czy jest spacja
	#prawdododobnie tutaj trzeba sprawdzic jaki to nawias jezeli prawy to nie zapisuj go ale zapisz do kolejki wszytskie operatory na stosie az do lewego nawiasu
	poprawym:
	
	move $a0, $s0
	move $a1, $s1
	move $a2, $v0
	jal save_token		#zapisanie znaku
	
l0_continue:			#operacja przejscia na kolejny byte z rownania
	addi $s2, $s2, 1 
  	lb $s3, ($s2)
	j l0_begin
	
rightbracket:

	move $a0, $s0 		#s0 stos -> a0
	jal peek_back_deque	#podglad gory stosu i zapisanie do v0
	move $s3, $v0
			
	lb $t0, ($s3)
	
 	beq $t0, 9, leftbracket
 			
 	move $a0, $s1
  	move $a1, $s3
	jal push_back_deque	
	
	move $a0, $s0 		#s0 stos -> a0
	jal pop_back_deque	
	
	j rightbracket
	
	leftbracket:
	
	#j failed_to_choose
	
	move $a0, $s0 		#s0 stos -> a0
	jal pop_back_deque	
	#move $a0, $s2 
	
 	j l0_continue

	
l0_end:
l1_start_shunting_yard:
	move $a0, $s0
	jal size_deque
  
	beq $v0, $0, l1_end_shunting_yard
  
	move $a0, $s0
	jal peek_back_deque
	move $s2, $v0

	move $a0, $s0
	jal pop_back_deque
  
	move $a0, $s1
	move $a1, $s2
	jal push_back_deque

	j l1_start_shunting_yard
l1_end_shunting_yard:
  	lw $ra ($sp)
  	addi $sp, $sp, 4

  	move $v0, $s1

  	jr $ra
  	
  	
match:
	sub $sp $sp 4
	sw $ra ($sp)

	li $t0, 10		# 10 = '\n' 
	beq $a0, $t0, return_null

	li $t0, 32		# 32 = ' '
	#beq $a0, $t0, return_null
	beq $a0, $t0, return_token_space
	
	li $t0, 124		# 124 = '|'
	#beq $a0, $t0, return_null
	beq $a0, $t0, return_token_abs

	li $t0, 42		# 42 = '*'
	beq $a0, $t0, return_token_mult

	li $t0, 47		# 47 = '/'
	beq $a0, $t0, return_token_div
	
	li $t0, 94		# 94 = '^'
	beq $a0, $t0, return_token_exp
	
	li $t0, 33		# 33 = '!'
	beq $a0, $t0, return_token_fac
	
	li $t0, 40		# 40 = '('
	beq $a0, $t0, return_token_lb
	
	li $t0, 41		# 41 = ')'
	beq $a0, $t0, return_token_rb

	li $t0, 43		# 43 = '+'
	beq $a0, $t0, return_token_sum

	li $t0, 45		# 45 = '-'
	beq $a0, $t0, return_token_sub

	li $t0, 61		# 61 = '='
	beq $a0, $t0, return_token_eq
	
	

	##DOPISAC ODWOTNOSC I BEZWZGLEDNA ==============================================================================================================!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	li $t0, 48		# 48 = '0'
	blt $a0, $t0, match_fail
	li $t0, 57		# 57 = '9'
	bgt $a0, $t0, match_fail

 	j return_token_num
 	
 match_fail:
  	j failed_to_parse
  
return_token_mult:
	la $t0, TOK_MULT
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_div:
	la $t0, TOK_DIV
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_abs:
	la $t0, TOK_ABS
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_exp:
	la $t0, TOK_EXP
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_fac:
	la $t0, TOK_FAC
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_sum:
	la $t0, TOK_SUM
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_lb:
	la $t0, TOK_LB
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_rb: #tutaj trzeba szukacj operacji itd
#	la $t0, TOK_RB
#	lb $a0, ($t0)
#	li $a1, 0
#	jal create_token
	li $v0, 2
	j end_match
  
return_token_sub:
	la $t0, TOK_SUB
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_eq:
	la $t0, TOK_EQ
	lb $a0, ($t0)
	li $a1, 0
	jal create_token
	j end_match
	
return_token_space:
	sub $a1, $a0, 48	# 48 = '0'
	la $t0, TOK_SPACE
	lb $a0, ($t0)
	
	jal create_token
	j end_match	
	
return_token_num:
	sub $a1, $a0, 48	# 48 = '0'

	la $t0, TOK_NUM
	lb $a0, ($t0)

	jal create_token
	j end_match
  
return_null:
	li $v0, 0
	j end_match
  
end_match:
	lw $ra ($sp)
	addi $sp, $sp, 4
	jr $ra

save_token:		#a0 stack, a1 queue, a2 token
	sub $sp $sp 20
	sw $ra ($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)

	move $s0, $a0
	move $s1, $a1
	move $s2, $a2 		#a2 token -> s2

	la $t0, TOK_NUM
	lb $t0, ($t0)

	lb $t1, ($s2)		# token s2 -> t1
	
	beq $t0, $t1, token_is_number
	
token_is_operator:
  	move $a0, $s0
  	jal size_deque
 	beq $v0, $0, l0_end_save_token

	move $a0, $s0 		#s0 stos -> a0
	jal peek_back_deque	#podglad gory stosu i zapisanie do v0
	move $s3, $v0		#v0 -> s3
	
  
	lb $t0, ($s3)
	

 	lb $t1, ($s2)
 	
 	beq $t1, 9, l0_end_save_token  #jezeli jestes lewyn nawiasem to nie patrzysz pod spod
 	
	bge $t0, $t1, l0_end_save_token
	
#	beq $t0, 10, prawynawias
  
	move $a0, $s0		#usuniecie ze stosu
	jal pop_back_deque
  
	move $a0, $s1		#wstawienie do kolejki
	move $a1, $s3
	jal push_back_deque

	j token_is_operator
	
	
prawynawias:
	#j failed_to_choose

	move $a0, $s0		#usuniecie ze stosu
	jal pop_back_deque
	
	jal peek_back_deque	#podglad gory stosu i zapisanie do v0
	move $s3, $v0		#v0 -> s3
	
	lb $t0, ($s3)
	
	lb $t1, ($s2)
	
	bge $t0, $t1, l0_end_save_token #nie wiem czy to ma sens xd
 	
 	beq $t0, 9, lewynawias
	
	move $a0, $s1		#wstawienie do kolejki
	move $a1, $s3
	jal push_back_deque
	
	j prawynawias
	
	lewynawias:
	move $a0, $s0		#usuniecie ze stosu
	jal pop_back_deque
	
	j end_save_token
	
	
	

l0_end_save_token:   #wrzucanie do stosu operatora
  	move $a0, $s0
  	move $a1, $s2
	jal push_back_deque

  	j end_save_token
  
token_is_number:	#wrzucanie do kolejki liczby
  	move $a0, $s1
  	move $a1, $s2
	jal push_back_deque

 	j end_save_token

end_save_token:
 	lw $ra ($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
 	lw $s3, 16($sp)

 	addi $sp, $sp, 20

 	jr $ra
 	
create_token:
	move $t0, $a0
 	move $t1, $a1

	li $a0, 4		#sizeof(Token) to jest byte
	li $v0, 9
	syscall
	move $t2, $v0

	sb $t0, ($t2)
	sb $t1, 1($t2)

	jr $ra
	
create_token_word:
	move $t0, $a0
 	move $t1, $a1

	li $a0, 4		#sizeof(Token) to jest word?
	li $v0, 9
	syscall
	move $t2, $v0

	sw $t0, ($t2)
	sw $t1, 1($t2)

	jr $ra
	
#==========================PROCEDURY KOLEJKI============================

create_deque:
	li $a0, 12		#sizeof(Deque)
	li $v0, 9
	syscall
	move $t0, $v0

	li $t1, 0
	sw $t1, ($t0)
	sw $t1, 4($t0)
	sw $t1, 8($t0)

	move $v0, $t0

	jr $ra

push_back_deque:		#(a0 Deque, a1 Token)
	move $t0, $a0

	lw $t1, ($t0)
	addiu $t1, $t1, 1
	sw $t1, ($t0)
  
	li $a0, 12
	li $v0, 9
	syscall
	move $t1, $v0

	lw $t2, 8($t0)
	sw $t2, ($t1)
	sw $0, 4($t1)
	sw $a1, 8($t1)

	beq $0, $t2, push_back_deque_c0
	sw $t1, 4($t2)
push_back_deque_c0:
	lw $t2, 4($t0)
  
	bne $0, $t2, push_back_deque_c1
	sw $t1, 4($t0)
push_back_deque_c1:
	sw $t1, 8($t0)

	jr $ra
	
push_front_deque:                #(a0 Deque, a1 Token)
	move $t0, $a0

	lw $t1, ($t0)
	addiu $t1, $t1, 1
	sw $t1, ($t0)

	li $a0, 12
	li $v0, 9
	syscall
	move $t1, $v0
	
	lw $t2, 4($t0)
	sw $t2, 4($t1)
	sw $a1, 8($t1)
	sw $0, ($t1)
	
	beq $0, $t2, push_front_deque_c0
	sw $t1, ($t2)
push_front_deque_c0:
	lw $t2, 8($t0)

	bne $0, $t2, push_front_deque_c1
	sw $t1, 8($t0)
push_front_deque_c1:
	sw $t1, 4($t0)

	jr $ra
	
peek_back_deque:
	lw $t0, 8($a0)
	lw $v0, 8($t0)

	jr $ra

peek_front_deque:
	lw $t0, 4($a0)
	lw $v0, 8($t0)

	jr $ra
	
pop_back_deque:
	move $t0, $a0
  
	lw $t1, 8($t0)

	lw $t2, ($t0)
	addi $t2, $t2, -1
	sw $t2, ($t0)

	lw $t2, ($t1)
	sw $t2, 8($t0)


	lw $t2, 8($t0)
	bne $t2, $0, c0_pop_back_deque
	sw $0, 4($t0)
	jr $ra

c0_pop_back_deque:
	sw $0, 4($t2)

	jr $ra

pop_front_deque:
	move $t0, $a0

	lw $t1, 4($t0)

	lw $t2, ($t0)
	addi $t2, $t2, -1
	sw $t2, ($t0)

	lw $t2, 4($t1)
	sw $t2, 4($t0)

	lw $t2, 4($t0)
	bne $t2, $0, c0_pop_front_deque
	sw $0, 8($t0)
	jr $ra
	
c0_pop_front_deque:
	sw $0, ($t2)

	jr $ra

size_deque:
	lw $v0, ($a0)

	jr $ra
	
#==================REVERSED POLISH NOTATION PARSER===========================

rpn_parser:
  	sub $sp $sp 4
 	sw $ra ($sp)

 	move $s0, $a0 #tworzenie nowego stosu?

  	jal create_deque
  	move $s1, $v0
  
l0_begin_rpn_parser:
	move $a0, $s0
	jal size_deque

	beq $v0, $0, l0_end_rpn_parser
  
	move $a0, $s0
	jal peek_front_deque
	
	la $t0, TOK_NUM  
	lb $t0, ($t0)
	lb $t1, ($v0)
	bne  $t0, $t1, is_not_num
	
	#TUTAJ TRZEBA ZROBIC WHILE'A TAKIEGO, ZE MNOZY I SUMUJE TEMP ZMIENNA
  	#jest numerek
  	li $t7, 10
  	lb $t5, 1($v0) #czyli tutaj mam warosc
  	mul $t6, $t6, $t7
 	#gdzies trzymaj 10 wstepne
  
  	add $t6, $t6, $t5
  	
  	j l0_continue_rpn_parse
  	
  
  
  	is_not_num:
  	
  	#przetlumacz t6 value na znak
  	
  	la $t0, TOK_SPACE  
  	lb $t0, ($t0)
	lb $t1, ($v0)
  	bne  $t0, $t1, is_operator
  	
  	beq $t6, 0, l0_continue_rpn_parse
  	
  	la $t0, TOK_NUM
  	lb $a0, ($t0)
  	
  	move $a1, $t6
  	jal create_token

  	move $a0, $s1
  	move $a1, $v0
  	jal push_back_deque
  	
  	li $t6, 0
  	
  	j l0_continue_rpn_parse
  	
  	
	la $t0, TOK_NUM   
	lb $t0, ($t0)
	lb $t1, ($v0)
	bne $t0, $t1, is_operator
	
	move $a0, $s1 	#ODLUZ NA STOS - TRZEBA ODLOZYC DOBRA LICZBE
	move $a1, $v0
 	jal push_back_deque

	j l0_continue_rpn_parse
  
is_operator:
	move $s4, $t1  
	#od razu sprawdz czy operator jednoargumentowy

	move $a0, $s1
	jal peek_back_deque
	lb $s2, 1($v0)
	#cvt.s.w $f2, $s2 #TRZEBA SPRAWDZIC CZY NIE BYL JUZ UZYWANY BO TUTAJ SIE NADPISUJE
	mtc1 $s2, $f2
	cvt.s.w $f2, $f2
	#lwc1   $f2, 1($v0)
 
	move $a0, $s1
	jal pop_back_deque

	la $t0, TOK_FAC
	lb $t0, ($t0)
	beq $t0, $s4, op_fac
	
	
	la $t0, TOK_ABS
	lb $t0, ($t0)
	beq $t0, $s4, op_abs

	move $a0, $s1
	jal peek_back_deque
	lb $s3, 1($v0)
	mtc1 $s3, $f3
	cvt.s.w $f3, $f3
	#cvt.s.w $f4, $s3
	#lwc1 $f4, 16($v0)
  
	move $a0, $s1
	jal pop_back_deque

	la $t0, TOK_SUM
	lb $t0, ($t0)
	beq $t0, $s4, op_sum

	la $t0, TOK_SUB
	lb $t0, ($t0)
	beq $t0, $s4, op_sub

	la $t0, TOK_MULT
	lb $t0, ($t0)
	beq $t0, $s4, op_mult
	
	la $t0, TOK_EXP
	lb $t0, ($t0)
	beq $t0, $s4, op_exp
	
	la $t0, TOK_DIV
	lb $t0, ($t0)
	beq $t0, $s4, op_div

	la $t0, TOK_EQ
	lb $t0, ($t0)
	beq $t0, $s4, op_eq

op_sum:
  	add $s2, $s2, $s3
  	add.s $f2, $f2, $f3
  	j op_end

op_sub:
  	sub $s2, $s3, $s2
  	sub.s $f2, $f3, $f2

  	j op_end

op_mult:
	mult $s2, $s3
  	mflo $s2
  	mul.s $f2, $f2, $f3
  	j op_end

op_div:
  	div $s2, $s3, $s2
  	div.s $f2, $f3, $f2
  	mflo $s2
  	j op_end
  	
op_exp: #procedura potegowania pls
	
	move $s6, $s2
	move $s2, $s3
	move $s3, $s6
	li $s6, 1
	pow:
	mul  $s6, $s6, $s2
	subi  $s3, $s3, 1
	bge  $s3 , 1, pow
	
	
#	pow:
#	mul.s $f12, $f12, $f2
#	sub.s $f4, $f4, $f6
#	c.le.s $f6, $f4
#	bc1t pow
	
	move $s2, $s6
  	j op_end
  	
  	
op_fac:
	#miejsce na silnie
	li $s6, 1
	fac:
		mul $s6, $s6, $s2
		subi $s2, $s2, 1
		bge $s2, 1, fac
	move $s2, $s6
  	j op_end
  	
op_abs:
	abs $s2, $s2
	abs.s $f2, $f2
	j op_end

op_eq:
  	beq $s2, $s3, eq
neq:
  	li $s2, 0
  	j op_end
eq:
  	li $s2, 1
	j op_end

op_end:
  	la $t0, TOK_FLOAT
  	#la $t0, TOK_FLOAT
  	lw $a0, ($t0)
 
 	#cvt.w.s $f2, $f2
 	#mfc1 $s2, $f2 #to nam usuwa juz informacje o przecinku
  	move $a1, $s2
  	jal create_token

  	move $a0, $s1
  	move $a1, $v0
  	jal push_back_deque
  	
l0_continue_rpn_parse:
	move $a0, $s0
	jal pop_front_deque

	j l0_begin_rpn_parser

l0_end_rpn_parser:
	move $a0, $s1
	jal peek_back_deque
 
	lb $s2, 1($v0)

	move $a0, $s1
	jal pop_back_deque

	move $v0, $s2

	lw $ra ($sp)
	addi $sp, $sp, 4

	jr $ra
	
	
  
