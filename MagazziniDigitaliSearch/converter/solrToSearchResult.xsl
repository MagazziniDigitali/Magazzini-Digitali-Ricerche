<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
    <xsl:template match="/">
        <xsl:if test="//response/result/doc">
            <table class="mdResultRice">
                <xsl:apply-templates select="//response/result/doc"/>
            </table>
        </xsl:if>
    </xsl:template>
    <xsl:template match="//response/result/doc">
        <tr>
            <td>
                <xsl:if test="arr[@name='tipoOggetto_show']">
                    Tipo Oggetto: <b>
                        <xsl:copy-of select="arr[@name='tipoOggetto_show']/str"/>
                    </b>
                </xsl:if>  
                <xsl:if test="arr[@name='originalFileName_show']">
                    Nome file Originale: <b>
                        <xsl:copy-of select="arr[@name='originalFileName_show']/str"/>
                    </b>
                </xsl:if>  
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>