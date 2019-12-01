package main.br.com.joyC.gaals;

public class SemanticError extends AnalysisError
{

    public Integer position;
    public SemanticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SemanticError(String msg)
    {
        super(msg);
    }

    @Override
    public int getPosition() {
        return this.position;
    }
}
