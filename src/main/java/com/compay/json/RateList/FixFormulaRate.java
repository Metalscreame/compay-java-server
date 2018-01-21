package com.compay.json.RateList;

public class FixFormulaRate
{
    private String formulaId;

    public String getFormulaId() { return this.formulaId; }

    public void setFormulaId(String formulaId) { this.formulaId = formulaId; }

    private String formula;

    public String getFormula() { return this.formula; }

    public void setFormula(String formula) { this.formula = formula; }

    private Attrs attrs;

    public Attrs getAttrs() { return this.attrs; }

    public void setAttrs(Attrs attrs) { this.attrs = attrs; }

    public FixFormulaRate(String formulaId, String formula, Attrs attrs) {
        this.formulaId = formulaId;
        this.formula = formula;
        this.attrs = attrs;
    }
}
