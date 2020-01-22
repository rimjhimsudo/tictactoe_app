package com.example.tictactoe_kot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {


    lateinit var board: Array<Array<Button>> //declaration of 2d array
    var player=true //representing player a strue n player 2 as false
    var turn_count=0
    var board_status=Array(3){IntArray(3)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //using synchronous binding??
        board= arrayOf(//initialistn of 2d array
            arrayOf(b1,b2,b3),
            arrayOf(b4,b5,b6),
            arrayOf(b7,b8,b9)
        )
        for (i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initboardstatus()
        resetbtn.setOnClickListener{
            turn_count=0
            player=true
            initboardstatus()
        }

    }
    private fun initboardstatus() {
        for (i in 0..2) {
            for(j in 0..2){
                board_status[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
                tv1.text=""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.b1->{
                updateValue(row=0,col=0,player=player)

            }
            R.id.b2->{
                updateValue(row=0,col=1,player=player)
            }
            R.id.b3->{
                updateValue(row=0,col=2,player=player)
            }
            R.id.b4->{
                updateValue(row=1,col=0,player=player)
            }
            R.id.b5 ->{
                updateValue(row=1,col=1,player=player)
            }
            R.id.b6 ->{
                updateValue(row=1,col=2,player=player)
            }
            R.id.b7->{
                updateValue(row=2,col=0,player=player)
            }
            R.id.b8->{
                updateValue(row=2,col=1,player=player)
            }
            R.id.b9->{
                updateValue(row=2,col=2,player=player)
            }
        }
        turn_count+=1
        player=!player
        if(player){
            updateDisplay("player X turn ")
        }
        else {
            updateDisplay("player O turn")
        }
        if(turn_count==9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        //horizontal match
        for(i in 0..2){
            if(board_status[i][0]==board_status[i][1] && board_status[i][0]==board_status[i][2]){
                if(board_status[i][0]==1){
                    updateDisplay("player x is winner")
                    break
                }
                else if(board_status[i][0]==0){
                    updateDisplay("player o is winner")
                    break
                }
            }
        }
        //vertical match
        for(i in 0..2){
            if(board_status[0][i]==board_status[1][i] && board_status[0][i]==board_status[2][i]){
                if(board_status[i][0]==1){
                    updateDisplay("player x is winner")
                    break
                }
                else if(board_status[i][0]==0){
                    updateDisplay("player o is winner")
                    break
                }
            }
        }
        //main diagonal
        if(board_status[0][0]==board_status[1][1] && board_status[0][0]==board_status[2][2]){
            if(board_status[0][0]==1){
                updateDisplay("player x is winner")
            }
            else if(board_status[0][0]==0){
                updateDisplay("player o is winner")
            }
        }
        //off diagonal
        if(board_status[0][2]==board_status[1][1] && board_status[0][2]==board_status[2][0]){
            if(board_status[0][2]==1){
                updateDisplay("player x is winner")
            }
            else if(board_status[0][2]==0){
                updateDisplay("player o is winner")
            }
        }

    }
    private fun disableBtns(){
        for (i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(s: String) {
            //tv1.setText(s)
        tv1.text=s

        if(s.contains("winner")){
            disableBtns()
        }
    }


    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val btntext=if(player) "X" else "O"
        val val1=if(player)1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(btntext)
        }
        board_status[row][col]=val1


    }

}
