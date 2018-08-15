
package companionPainter;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import at.yeoman.mutabor.serialization.TypeId;
import at.yeoman.mutabor.serialization.Serializer;
import at.yeoman.mutabor.serialization.typeManagers.StructTypeManager;

public final class Id extends at.yeoman.mutabor.Struct
{
    private final long m_gid;
    private final long m_eid;
    private final long m_version;
    
    public Id(long gid, long eid, long version)
    {
        m_gid = gid;
        m_eid = eid;
        m_version = version;
    }
    
    public long getGid()
    {
        return m_gid;
    }
    
    public long getEid()
    {
        return m_eid;
    }
    
    public long getVersion()
    {
        return m_version;
    }
    
    public Id withGid(long value)
    {
        return new Id(value, m_eid, m_version);
    }
    
    public Id withEid(long value)
    {
        return new Id(m_gid, value, m_version);
    }
    
    public Id withVersion(long value)
    {
        return new Id(m_gid, m_eid, value);
    }
    
    @Override
    public int hashCode()
    {
        int result = 1;
        result *= 37;
        result += Long.hashCode(m_gid);
        result *= 37;
        result += Long.hashCode(m_eid);
        result *= 37;
        result += Long.hashCode(m_version);
        return result;
    }
    
    @Override
    public boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (getClass() != otherObject.getClass()) return false;
        Id other = (Id)otherObject;
        if (m_gid != other.m_gid) return false;
        if (m_eid != other.m_eid) return false;
        if (m_version != other.m_version) return false;
        return true;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Id {");
        sb.append(" gid: ");
        sb.append(Long.toString(m_gid));
        sb.append(", eid: ");
        sb.append(Long.toString(m_eid));
        sb.append(", version: ");
        sb.append(Long.toString(m_version));
        sb.append(" }");
        return sb.toString();
    }
    
    public void writeToStream(OutputStream out)
    throws IOException
    {
        Serializer.writeTypeId(TypeId.Struct, out);
        Serializer.writeCompressedInt64(m_gid, out);
        Serializer.writeCompressedInt64(m_eid, out);
        Serializer.writeCompressedInt64(m_version, out);
    }
    
    public static Id createFromStream(InputStream in)
    throws IOException
    {
        Serializer.checkTypeId(TypeId.Struct, in);
        return createFromStreamAfterIdentification(in);
    }
    
    public static Id createFromStreamAfterIdentification(InputStream in)
    throws IOException
    {
        long a_gid = Serializer.readCompressedInt64(in);
        long a_eid = Serializer.readCompressedInt64(in);
        long a_version = Serializer.readCompressedInt64(in);
        return new Id(a_gid, a_eid, a_version);
    }
    
    public static final StructTypeManager<Id> TypeManager = new StructTypeManager<>(in -> Id.createFromStream(in));
}
