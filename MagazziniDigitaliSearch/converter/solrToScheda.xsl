<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
    <xsl:template match="/">
        <xsl:if test="//response/result/doc">
            <xsl:apply-templates select="//response/result/doc"/>
        </xsl:if>
    </xsl:template>
    <xsl:template match="//response/result/doc">
        <xsl:if test="str[@name='_root_']">
            Scheda collegata: 
            <xsl:for-each select="str[@name='_root_']">
                <a title="_root_">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="str[@name='id']">
            ID: 
            <xsl:for-each select="str[@name='id']">
                <a title="id">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="str[@name='_tipo_']">
            _tipo_: 
            <xsl:for-each select="str[@name='_tipo_']">
                <a title="_tipo_">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='tipoOggetto_show']">
            tipoOggetto: 
            <xsl:for-each select="arr[@name='tipoOggetto_show']/str">
                <a title="tipoOggetto">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='provenienzaOggetto_show']">
            provenienzaOggetto: 
            <xsl:for-each select="arr[@name='provenienzaOggetto_show']/str">
                <a title="provenienzaOggetto">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='tipoContenitore_show']">
            tipoContenitore: 
            <xsl:for-each select="arr[@name='tipoContenitore_show']/str">
                <a title="tipoContenitore">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='objectIdentifier_show']">
            objectIdentifier: 
            <xsl:for-each select="arr[@name='objectIdentifier_show']/str">
                <a title="objectIdentifier">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='fileType_show']">
            fileType: 
            <xsl:for-each select="arr[@name='fileType_show']/str">
                <a title="fileType">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='actualFileName_show']">
            actualFileName: 
            <xsl:for-each select="arr[@name='actualFileName_show']/str">
                <a title="actualFileName">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='compositionLevel_show']">
            compositionLevel: 
            <xsl:for-each select="arr[@name='compositionLevel_show']/str">
                <a title="compositionLevel">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='sha1_show']">
            sha1: 
            <xsl:for-each select="arr[@name='sha1_show']/str">
                <a title="sha1">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='size_show']">
            size: 
            <xsl:for-each select="arr[@name='size_show']/str">
                <a title="size">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='mimeType_show']">
            mimeType: 
            <xsl:for-each select="arr[@name='mimeType_show']/str">
                <a title="mimeType">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='promon_show']">
            promon: 
            <xsl:for-each select="arr[@name='promon_show']/str">
                <a title="promon">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='originalFileName_show']">
            originalFileName: 
            <xsl:for-each select="arr[@name='originalFileName_show']/str">
                <a title="originalFileName">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='rights_show']">
            rights: 
            <xsl:for-each select="arr[@name='rights_show']/str">
                <a title="rights">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='tarIndex_show']">
            tarIndex: 
            <xsl:for-each select="arr[@name='tarIndex_show']/str">
                <a title="tarIndex">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='relationshipType_show']">
            relationshipType: 
            <xsl:for-each select="arr[@name='relationshipType_show']/str">
                <a title="relationshipType">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='eventID_show']">
            eventID: 
            <xsl:for-each select="arr[@name='eventID_show']/str">
                <a title="eventID">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='eventType_show']">
            eventType: 
            <xsl:for-each select="arr[@name='eventType_show']/str">
                <a title="eventType">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='eventDate_show']">
            eventDate: 
            <xsl:for-each select="arr[@name='eventDate_show']/str">
                <a title="eventDate">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='eventDetail_show']">
            eventDetail: 
            <xsl:for-each select="arr[@name='eventDetail_show']/str">
                <a title="eventDetail">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='eventOutCome_show']">
            eventOutCome: 
            <xsl:for-each select="arr[@name='eventOutCome_show']/str">
                <a title="eventOutCome">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='agentDepositante_show']">
            agentDepositante: 
            <xsl:for-each select="arr[@name='agentDepositante_show']/str">
                <a title="agentDepositante">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='agentSoftware_show']">
            agentSoftware: 
            <xsl:for-each select="arr[@name='agentSoftware_show']/str">
                <a title="agentSoftware">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='nodo_show']">
            nodo: 
            <xsl:for-each select="arr[@name='nodo_show']/str">
                <a title="nodo">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='tipoDocumento_show']">
            tipoDocumento: 
            <xsl:for-each select="arr[@name='tipoDocumento_show']/str">
                <a title="tipoDocumento">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='bid_show']">
            bid: 
            <xsl:for-each select="arr[@name='bid_show']/str">
                <a title="bid">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='bni_show']">
            bni: 
            <xsl:for-each select="arr[@name='bni_show']/str">
                <a title="bni">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='titolo_show']">
            titolo: 
            <xsl:for-each select="arr[@name='titolo_show']/str">
                <a title="titolo">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='autore_show']">
            autore: 
            <xsl:for-each select="arr[@name='autore_show']/str">
                <a title="autore">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='pubblicazione_show']">
            pubblicazione: 
            <xsl:for-each select="arr[@name='pubblicazione_show']/str">
                <a title="pubblicazione">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='soggetto_show']">
            soggetto: 
            <xsl:for-each select="arr[@name='soggetto_show']/str">
                <a title="soggetto">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='descrizione_show']">
            descrizione: 
            <xsl:for-each select="arr[@name='descrizione_show']/str">
                <a title="descrizione">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='contributo_show']">
            contributo: 
            <xsl:for-each select="arr[@name='contributo_show']/str">
                <a title="contributo">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='data_show']">
            data: 
            <xsl:for-each select="arr[@name='data_show']/str">
                <a title="data">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='tiporisorsa_show']">
            tiporisorsa: 
            <xsl:for-each select="arr[@name='tiporisorsa_show']/str">
                <a title="tiporisorsa">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='formato_show']">
            formato: 
            <xsl:for-each select="arr[@name='formato_show']/str">
                <a title="formato">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='fonte_show']">
            fonte: 
            <xsl:for-each select="arr[@name='fonte_show']/str">
                <a title="fonte">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='lingua_show']">
            lingua: 
            <xsl:for-each select="arr[@name='lingua_show']/str">
                <a title="lingua">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='relazione_show']">
            relazione: 
            <xsl:for-each select="arr[@name='relazione_show']/str">
                <a title="relazione">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='copertura_show']">
            copertura: 
            <xsl:for-each select="arr[@name='copertura_show']/str">
                <a title="copertura">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='gestionediritti_show']">
            gestionediritti: 
            <xsl:for-each select="arr[@name='gestionediritti_show']/str">
                <a title="gestionediritti">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='biblioteca_show']">
            biblioteca: 
            <xsl:for-each select="arr[@name='biblioteca_show']/str">
                <a title="biblioteca">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='inventario_show']">
            inventario: 
            <xsl:for-each select="arr[@name='inventario_show']/str">
                <a title="inventario">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='collocazione_show']">
            collocazione: 
            <xsl:for-each select="arr[@name='collocazione_show']/str">
                <a title="collocazione">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='piecegr_show']">
            piecegr: 
            <xsl:for-each select="arr[@name='piecegr_show']/str">
                <a title="piecegr">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='piecedt_show']">
            piecedt: 
            <xsl:for-each select="arr[@name='piecedt_show']/str">
                <a title="piecedt">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='piecein_show']">
            piecein: 
            <xsl:for-each select="arr[@name='piecein_show']/str">
                <a title="piecein">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
        <xsl:if test="arr[@name='indexed_show']">
            indexed: 
            <xsl:for-each select="arr[@name='indexed_show']/str">
                <a title="indexed">
                    <xsl:attribute name="href"><xsl:copy-of select="child::text()" /></xsl:attribute>
                    <b>
                        <xsl:copy-of select="child::text()"/>
                    </b>
                </a>
            </xsl:for-each>
            <br/>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>